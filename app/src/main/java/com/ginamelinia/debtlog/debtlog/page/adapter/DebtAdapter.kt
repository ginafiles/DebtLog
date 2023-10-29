package com.ginamelinia.debtlog.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ginamelinia.debtlog.databinding.ItemDebtBinding
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class DebtAdapter(
    private val homeViewModel: HomeViewModel,
    private val updateDebt: (debtEntity: DebtEntity) -> Unit,
    private val deleteDebt: (debtEntity: DebtEntity) -> Unit
) : ListAdapter<DebtEntity, DebtViewHolder>(
    DebtDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val binding = ItemDebtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DebtViewHolder(
            binding = binding,
            homeViewModel = homeViewModel,
            updateDebt = updateDebt,
            deleteDebt = deleteDebt
            )
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        val debtEntity = getItem(position)
        holder.bind(debtEntity)
    }

}