package com.ginamelinia.debtlog.page.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ginamelinia.debtlog.repository.data.Debt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel : ViewModel() {

    private var i = 0
    private val debtList: MutableList<Debt> = mutableListOf()

    private val _debts= MutableLiveData<List<Debt>>()
    val debts: LiveData<List<Debt>> = _debts

    fun provideData() {
        viewModelScope.launch(Dispatchers.IO) {
            _debts.postValue(debtList)
        }
    }

    fun formateDate(date: Date): String {
        return  SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }

    fun createDebt() {
        val debt = Debt("Gina ke-${i + 1}", "Rp${i + 1}", Date())
        debtList.add(debt)
        i++
        _debts.value = debtList.toList()
    }
}