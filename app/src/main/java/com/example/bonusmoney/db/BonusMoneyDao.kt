package com.example.bonusmoney.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BonusMoneyDao {

    @Upsert
    suspend fun upsertAll(companies: List<CompanyEntity>)

    @Query("SELECT * FROM companyentity")
    fun pagingSource(): PagingSource<Int, CompanyEntity>

    @Query("DELETE FROM companyentity")
    suspend fun nuke(): Int
}