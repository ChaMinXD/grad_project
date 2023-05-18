package com.example.myapplication.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilteringDAO {
    @Query("SELECT * FROM filtering")
    fun getAll():List<FilteringEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWord(filteringEntity: FilteringEntity)
    @Delete
    fun deleteWord(filteringEntity: FilteringEntity)
}