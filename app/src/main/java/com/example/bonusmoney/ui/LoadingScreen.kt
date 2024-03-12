package com.example.bonusmoney.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.bonusmoney.api.Company
import com.example.bonusmoney.utils.Config
import retrofit2.HttpException

@Composable
fun LoadingScreen(companies: LazyPagingItems<Company>) {
    Column(
        modifier = Modifier
            .background(colorResource(id = Config.lightGray))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .background(colorResource(id = Config.white))
                .fillMaxWidth()
                .padding(16.dp),
            text = "Управление картами",
            color = colorResource(id = Config.blue),
            fontSize = 20.sp,
            fontFamily = Config.segoe,
            textAlign = TextAlign.Center
        )

        val ctx = LocalContext.current
        LaunchedEffect(key1 = companies.loadState) {
            if (companies.loadState.refresh is LoadState.Error) {
                val error = (companies.loadState.refresh as LoadState.Error).error
                if (error is HttpException) {
                    when (error.code()) {
                        401 -> toast(ctx, "ошибка авторизации")
                        400 -> toast(ctx, error.message())
                        500 -> toast(ctx, "все упало")
                        else -> toast(ctx, "неизвестная ошибка")
                    }
                }
            }
            
        }

        Box {
            if (companies.loadState.refresh is LoadState.Loading) {
                Column {
                    LargeSpace()
                    Preloader(modifier = Modifier.fillMaxWidth())
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(companies) {company ->
                        if (company != null) {
                            Column {
                                LargeSpace()
                                CompanyItem(
                                    company = company,
                                    context = ctx,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                    item { LargeSpace() }
                    item {
                        if (companies.loadState.append is LoadState.Loading) {
                            Column {
                                Preloader(modifier = Modifier.fillMaxWidth())
                                LargeSpace()
                            }
                        }
                    }
                }
            }
        }
    }
}

fun toast(ctx: Context, text: String) {
    Toast.makeText(
        ctx,
        text,
        Toast.LENGTH_SHORT
    ).show()
}