package com.ginamelinia.debtlog.page.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ginamelinia.debtlog.databinding.ItemDebtBinding
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class DebtViewHolder(
    private val binding: ItemDebtBinding,
    private val homeViewModel: HomeViewModel,
    private val updateDebt: (debtEntity: DebtEntity) -> Unit,
    private val deleteDebt: (debtEntity: DebtEntity) -> Unit,


) : RecyclerView.ViewHolder(binding.root) {
    fun bind(debtEntity: DebtEntity) {
        binding.textViewName.text = debtEntity.name
        binding.textViewAmount.text = debtEntity.moneyAmount

        binding.root.setOnClickListener {
            updateDebt(debtEntity)
        }

        binding.root.setOnLongClickListener {
            deleteDebt(debtEntity)
            true
        }
    }
}