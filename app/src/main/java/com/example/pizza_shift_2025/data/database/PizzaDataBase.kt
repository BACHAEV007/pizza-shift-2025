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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PizzaDataBase::class.java,
            "pizza_database"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<PizzaDataBase>().pizzaDao() }
}

@Database(entities = [PizzaEntity::class], version = 2)
@TypeConverters(PizzaConverter::class, SizeConverter::class, DoughConverter::class)
abstract class PizzaDataBase : RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao
}