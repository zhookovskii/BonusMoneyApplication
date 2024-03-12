package com.example.bonusmoney.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonusmoney.utils.Config

@Composable
fun Preloader(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp),
            color = colorResource(id = Config.black)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Подгрузка компаний",
            color = colorResource(id = Config.black),
            fontSize = 14.sp,
            fontFamily = Config.segoe,
            textAlign = TextAlign.Center
        )
    }
}