package com.example.bonusmoney

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.bonusmoney.api.BonusMoneyApi
import com.example.bonusmoney.api.BonusMoneyRemoteMediator
import com.example.bonusmoney.db.BonusMoneyDatabase
import com.example.bonusmoney.db.CompanyEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class BonusMoneyApp : Application() {

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single<Moshi> {
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            }
            single<BonusMoneyApi> {
                Retrofit.Builder()
                    .baseUrl("http://devapp.bonusmoney.pro/mobileapp/")
                    .addConverterFactory(
                        MoshiConverterFactory.create(get<Moshi>())
                    )
                    .build()
                    .create()
            }
            single<BonusMoneyDatabase> {
                Room.databaseBuilder(
                    this@BonusMoneyApp,
                    BonusMoneyDatabase::class.java,
                    "bonusmoney.db"
                ).build()
            }
            single<Pager<Int, CompanyEntity>> {
                Pager(
                    config = PagingConfig(pageSize = 5),
                    remoteMediator = BonusMoneyRemoteMediator(
                        db = get<BonusMoneyDatabase>(),
                        api = get<BonusMoneyApi>(),
                        moshi = get<Moshi>()
                    ),
                    pagingSourceFactory = {
                        get<BonusMoneyDatabase>().dao.pagingSource()
                    }
                )
            }
        }

        startKoin {
            androidLogger()
            androidContext(this@BonusMoneyApp)
            modules(appModule)
        }
    }
}