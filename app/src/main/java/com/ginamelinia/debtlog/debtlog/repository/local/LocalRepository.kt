package com.ginamelinia.debtlog.repository.local

import androidx.lifecycle.LiveData
import com.ginamelinia.debtlog.repository.DebtsRepository
import com.ginamelinia.debtlog.repository.local.room.database.DebtLogDatabase
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class LocalRepository(
    private val debtLogDatabase: DebtLogDatabase
) : DebtsRepository{
    private val debtDAO = debtLogDatabase.debtDao()

    override val selectAllDebts: LiveData<List<DebtEntity>> = debtDAO.selectAllDebts()
    override suspend fun addDebt(debtEntity: DebtEntity) {
        debtDAO.insertDebts(debtEntity)
    }

    override suspend fun updateDebt(debtEntity: DebtEntity) {
        debtDAO.updateDebts(debtEntity)
    }

    override suspend fun deleteDebt(debtEntity: DebtEntity) {
        debtDAO.deleteDebt(debtEntity)
    }

}