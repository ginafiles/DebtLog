package com.ginamelinia.debtlog.repository.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class DebtEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name= "moneyAmount")
    val moneyAmount: String
)