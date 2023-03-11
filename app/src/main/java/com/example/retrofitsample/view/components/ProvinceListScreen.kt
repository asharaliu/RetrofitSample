package com.example.retrofitsample.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofitsample.model.Country
import com.example.retrofitsample.model.Province

@Composable
fun ProvinceListScreen(provinceList: List<Province>) {
    if (provinceList.isEmpty()) {
        Text(modifier = Modifier.fillMaxSize(), text = "No Provinces", textAlign = TextAlign.Center)
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .testTag("Province List")
                .fillMaxWidth()
        ) {
            items(provinceList) {
                ProvinceItem(it)
            }
            item { LazyColumnBottomSpacer() }
        }
    }
}

@Composable
fun ProvinceItem(province: Province?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = province?.name.toString(),
            color = Color.Black
        )
    }
}

/**
 * ******************************************************
 * ********************* PREVIEWS ***********************
 * ******************************************************
 */

@Preview
@Composable
fun PreviewProvinceItem() {
    ProvinceItem(Province("Abia State", "AB"))
}