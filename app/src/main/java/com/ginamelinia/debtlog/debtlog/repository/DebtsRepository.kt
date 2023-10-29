package com.ginamelinia.debtlog.repository

import androidx.lifecycle.LiveData
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

interface DebtsRepository{
    val selectAllDebts: LiveData<List<DebtEntity>>
    suspend fun addDebt(debtEntity: DebtEntity)
    suspend fun updateDebt(debtEntity: DebtEntity)
    suspend fun deleteDebt(debtEntity: DebtEntity)

}