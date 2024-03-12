package com.example.bonusmoney.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BonusMoneyApi {

    @POST("getAllCompanies")
    suspend fun getAllCards(
        @Header("TOKEN") token: String,
        @Body body: RequestBody
    ): Companies

    @POST("getAllCompaniesIdeal")
    suspend fun getAllCardsIdeal(
        @Header("TOKEN") token: String,
        @Body body: RequestBody
    ): Companies
}