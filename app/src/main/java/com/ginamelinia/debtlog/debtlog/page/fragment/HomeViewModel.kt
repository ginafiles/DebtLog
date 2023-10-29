package com.ginamelinia.debtlog.page.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ginamelinia.debtlog.repository.DebtsRepository
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DebtsRepository
) : ViewModel() {

    val allDebts = repository.selectAllDebts

    fun addDebt(debtEntity: DebtEntity) {
        viewModelScope.launch {
            repository.addDebt(debtEntity)
        }
    }

    fun updateDebt(debtEntity: DebtEntity) {
        viewModelScope.launch {
            repository.updateDebt(debtEntity)
        }
    }

    fun deleteDebt(debtEntity: DebtEntity) {
        viewModelScope.launch {
            repository.deleteDebt(debtEntity)
        }
    }
}