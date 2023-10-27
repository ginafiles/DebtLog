package com.ginamelinia.debtlog.repository.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ginamelinia.debtlog.repository.local.room.dao.DebtDAO
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

@Database(entities = [DebtEntity::class], version = 1)
abstract class DebtDatabase : RoomDatabase(){

    companion object {
        const val DATABASE_NAME = "noteDB"
    }
    abstract fun noteDao(): DebtDAO
}