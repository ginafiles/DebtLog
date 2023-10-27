package com.ginamelinia.debtlog.page.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.databinding.ItemDebtBinding
import com.ginamelinia.debtlog.repository.data.Debt

class DebtViewHolder(
    private val binding: ItemDebtBinding,
    private val homeViewModel: HomeViewModel
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(debt: Debt) {
        binding.textViewName.text = debt.name
        binding.textViewAmount.text = debt.moneyAmount
        binding.textViewDate.text = homeViewModel.formateDate(debt.date)
    }
}