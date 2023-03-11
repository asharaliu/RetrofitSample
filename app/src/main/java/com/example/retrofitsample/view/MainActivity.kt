package com.example.retrofitsample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import com.example.retrofitsample.network.RequestStateRender
import com.example.retrofitsample.view.components.CountryListScreen
import com.example.retrofitsample.view.components.ErrorScreen
import com.example.retrofitsample.view.components.ProvinceListScreen
import com.example.retrofitsample.view.components.ShimmerAnimation
import com.example.retrofitsample.view.navigation.HomeNavigationGraph
import com.example.retrofitsample.view.navigation.Screen
import com.example.retrofitsample.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                HomeNavigationGraph()
            }
        }
    }
}

/**
 * Home country list screen implementation
 */
@Composable
fun CountryListScreenRoute(
    navController: NavHostController,
    countryViewModel: CountryViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Country Screen" },
        color = MaterialTheme.colorScheme.background
    ) {
        RequestStateRender(
            state = countryViewModel.countryListState.collectAsState(),
            onError = { ErrorScreen() },
            onLoading = {ShimmerAnimation()}) {
            if (it.error) {
                ErrorScreen()
            } else {
                CountryListScreen(countryList = it.data) { clickedItem ->
                    navController.navigate(Screen.Provinces.route + "/" + clickedItem)
                }
            }
        }
    }
}

/**
 * Second screen - province list screen implementation
 */
@Composable
fun ProvinceListScreenRoute(iso2: String?, countryViewModel:CountryViewModel) {
    CallProvinceAPI(countryViewModel, iso2)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Province Screen" },
        color = MaterialTheme.colorScheme.background
    ) {
        RequestStateRender(
            state = countryViewModel.provinceListState.collectAsState(),
            onError = { ErrorScreen() },
            onLoading = { ShimmerAnimation()}) {
            if (it.error) {
                ErrorScreen()
            } else {
                ProvinceListScreen(
                    provinceList =
                    it.data.states
                )
            }
        }
    }
}

@Composable
private fun CallProvinceAPI(
    countryViewModel: CountryViewModel,
    iso2: String?
) {
    val initialApiCalled = rememberSaveable { mutableStateOf(false) }
    // Calling the country list
    if (!initialApiCalled.value) {
        LaunchedEffect(key1 = Unit) {
            countryViewModel.getProvinceList(iso2)
            initialApiCalled.value = true
        }
    }
}

