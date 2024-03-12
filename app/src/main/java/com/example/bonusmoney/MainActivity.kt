package com.example.bonusmoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.ui.res.colorResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bonusmoney.db.BonusMoneyDatabase
import com.example.bonusmoney.ui.Navigation
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: BonusMoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = colorResource(id = R.color.lightGray)) {
                Navigation(
                    viewModel.companiesFlow.collectAsLazyPagingItems()
                )
            }
        }
    }
}

