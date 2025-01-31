package com.example.pizza_shift_2025.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizza_shift_2025.AppContext
import com.example.pizza_shift_2025.data.dao.PizzaDao
import com.example.pizza_shift_2025.data.dbconverter.DoughConverter
import com.example.pizza_shift_2025.data.dbconverter.PizzaConverter
import com.example.pizza_shift_2025.data.dbconverter.SizeConverter
import com.example.pizza_shift_2025.data.dbentity.PizzaEntity

@Database(entities = [PizzaEntity::class],
    version = 2)
@TypeConverters(PizzaConverter::class, SizeConverter::class, DoughConverter::class)
abstract class PizzaDataBase : RoomDatabase() {
    abstract fun PizzaDao(): PizzaDao

    companion object{
        @Volatile
        private var INSTANCE: PizzaDataBase? = null
        fun getDatabase(context: Context): PizzaDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDataBase::class.java,
                    "pizza_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}