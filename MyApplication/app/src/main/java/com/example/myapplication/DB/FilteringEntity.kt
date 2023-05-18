package com.example.myapplication.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "filtering")
data class FilteringEntity(
    @PrimaryKey
    val filteringWord:String=""
)
