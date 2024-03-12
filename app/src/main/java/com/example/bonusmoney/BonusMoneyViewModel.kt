package com.example.bonusmoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.bonusmoney.db.CompanyEntity
import com.example.bonusmoney.db.asCompany
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BonusMoneyViewModel : ViewModel(), KoinComponent {
    private val pager: Pager<Int, CompanyEntity> by inject()

    val companiesFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.asCompany() }
        }
        .cachedIn(viewModelScope)
}