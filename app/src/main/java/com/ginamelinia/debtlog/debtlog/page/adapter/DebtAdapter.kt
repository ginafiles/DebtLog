package com.ginamelinia.debtlog.page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ginamelinia.debtlog.page.fragment.HomeViewModel
import com.ginamelinia.debtlog.databinding.ItemDebtBinding
import com.ginamelinia.debtlog.repository.data.Debt

class DebtAdapter(private val homeViewModel: HomeViewModel) : ListAdapter<Debt, DebtViewHolder>(
    DebtDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val binding = ItemDebtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DebtViewHolder(
            binding = binding,
            homeViewModel = homeViewModel
            )
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        val debt = getItem(position)
        holder.bind(debt)
    }

}