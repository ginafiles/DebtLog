package com.ginamelinia.debtlog.page.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ginamelinia.debtlog.repository.local.room.entity.DebtEntity

class DebtDiffCallback : DiffUtil.ItemCallback<DebtEntity>(){
    override fun areItemsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
        return oldItem.name == newItem.name && oldItem.moneyAmount == newItem.moneyAmount
    }

}