package com.example.myapplication.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilteringEntity::class], version = 1)
abstract class FilteringDatabase:RoomDatabase() {
    abstract fun filteringDao(): FilteringDAO
    companion object{
        private var instance: FilteringDatabase?=null
        @Synchronized
        fun getInstance(context: Context): FilteringDatabase? {
            if(instance ==null)
                synchronized(FilteringDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilteringDatabase::class.java,
                        "filtering.db"
                    )
                        .build()
                }
            return instance
        }
        fun destroyInstance(){
            instance =null
        }
    }
}