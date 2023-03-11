package com.example.retrofitsample.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.retrofitsample.model.Country
import com.example.retrofitsample.R

@Composable
fun CountryListScreen(countryList: List<Country>, onItemClick: (iso: String?) -> Unit) {
    if (countryList.isEmpty()) {
        Text(modifier = Modifier.fillMaxSize(), text = "No countries", textAlign = TextAlign.Center)
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("Country List")
        ) {
            items(countryList) {
                CountryItem(it, onItemClick)
            }
            item { LazyColumnBottomSpacer() }
        }
    }
}

@Composable
fun CountryItem(
    country: Country,
    onItemClick: (iso: String?) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .testTag("Country List Item")
            .padding(5.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable { onItemClick(country.iso2) }
            .fillMaxWidth()
            .padding(8.dp)) {

        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .padding(5.dp)
                .clip(CircleShape),
            placeholder = painterResource(R.drawable.place_holder),
            error = painterResource(R.drawable.place_holder_error),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.flag)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = ""
        )

        Text(modifier = Modifier.padding(5.dp), text = country.name.toString(), color = Color.White)
    }
}

/**
 * ******************************************************
 * ********************* PREVIEWS ***********************
 * ******************************************************
 */

@Preview
@Composable
fun PreviewCountryItem() {
    CountryItem(
        country = Country(
            "AGO",
            "https://upload.wikimedia.org/wikipedia/commons/9/9d/Flag_of_Angola.svg",
            "Angola",
            "AO"
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCountryList(navHostController: NavHostController? = null) {
    CountryListScreen(
        listOf(
            Country(
                "AGO",
                "https://upload.wikimedia.org/wikipedia/commons/9/9d/Flag_of_Angola.svg",
                "Angola",
                "AO"
            ),
            Country(
                "AIA",
                "https://upload.wikimedia.org/wikipedia/commons/b/b4/Flag_of_Anguilla.svg",
                "Anguilla",
                "AI"
            ),
            Country(
                "BTN",
                "https://upload.wikimedia.org/wikipedia/commons/b/bf/Flag_of_Bermuda.svg",
                "Bermuda",
                "BT"
            )
        )
    ) {}
}