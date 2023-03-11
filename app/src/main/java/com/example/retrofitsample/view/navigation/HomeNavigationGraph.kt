package com.example.retrofitsample.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofitsample.util.Const
import com.example.retrofitsample.view.CountryListScreenRoute
import com.example.retrofitsample.view.ProvinceListScreenRoute
import com.example.retrofitsample.viewmodel.CountryViewModel

sealed class Screen(val route: String) {
    object Countries : Screen(Const.SCREEN_LINK_COUNTRY)
    object Provinces : Screen(Const.SCREEN_LINK_PROVINCE)
}

@Composable
fun HomeNavigationGraph() {
    val navController = rememberNavController()
    val countryViewModel = hiltViewModel<CountryViewModel>()

    HomeNavigationRoute(countryViewModel, navController)
}

@Composable
fun HomeNavigationRoute(
    countryViewModel: CountryViewModel,
    navController: NavHostController
) {
    // Calling the country list
    LaunchedEffect(key1 = Unit) {
        countryViewModel.getCountryList()
    }

    HomeNavHost(navController, countryViewModel)
}

@Composable
fun HomeNavHost(navController: NavHostController, countryViewModel: CountryViewModel) {
    NavHost(navController, startDestination = Screen.Countries.route) {
        composable(Screen.Countries.route) {
            CountryListScreenRoute(navController, countryViewModel)
        }
        composable(
            "${Screen.Provinces.route}/{${Const.ARG_COUNTRY_ID}}", listOf(
                navArgument(Const.ARG_COUNTRY_ID) { type = NavType.StringType })
        ) {
            ProvinceListScreenRoute(it.arguments?.getString(Const.ARG_COUNTRY_ID), countryViewModel)
        }
    }
}