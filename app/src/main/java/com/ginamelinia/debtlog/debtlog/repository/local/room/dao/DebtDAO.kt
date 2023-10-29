package com.ginamelinia.debtlog.repository.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

@Dao
interface DebtDAO {
    @Query("SELECT * FROM debts")
    fun selectAllDebts(): LiveData<List<DebtEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDebts(debtEntity: DebtEntity)

    @Update
    suspend fun updateDebts(debtEntity: DebtEntity)

    @Delete
    suspend fun deleteDebt(debtEntity: DebtEntity)
}