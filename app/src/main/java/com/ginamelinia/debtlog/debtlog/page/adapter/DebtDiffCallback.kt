package com.ginamelinia.debtlog.page.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ginamelinia.debtlog.repository.data.Debt

class DebtDiffCallback : DiffUtil.ItemCallback<Debt>(){
    override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
        return oldItem.date == newItem.date
    }

}