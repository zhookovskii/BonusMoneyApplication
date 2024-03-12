package com.example.bonusmoney.api

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bonusmoney.db.BonusMoneyDatabase
import com.example.bonusmoney.db.CompanyEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.IOException
import java.lang.RuntimeException

@OptIn(ExperimentalPagingApi::class)
class BonusMoneyRemoteMediator(
    private val db: BonusMoneyDatabase,
    private val api: BonusMoneyApi,
    private val moshi: Moshi
): RemoteMediator<Int, CompanyEntity>() {

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CompanyEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        lastItem.id + 1
                    }
                }
            }
            val json = moshi
                .adapter<BonusMoneyRequest>()
                .toJson(
                    BonusMoneyRequest(loadKey, state.config.pageSize)
                )
            val request = json
                .toRequestBody("application/json".toMediaTypeOrNull())
            delay(2000L)
            Log.d("mediator","load key is $loadKey, load type is $loadType")
            val companies = api
                .getAllCardsIdeal("123", request)
                .companies
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.nuke()
                }
                val entities = companies.mapIndexed { index, company ->
                    company.asEntity(loadKey + index)
                }
                db.dao.upsertAll(entities)
            }
            MediatorResult.Success(
                endOfPaginationReached = companies.isEmpty()
            )
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }

}