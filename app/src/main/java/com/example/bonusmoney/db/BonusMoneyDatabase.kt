package com.example.bonusmoney.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyEntity::class],
    version = 1
)
abstract class BonusMoneyDatabase : RoomDatabase() {
    abstract val dao: BonusMoneyDao
}