package com.example.pizza_shift_2025.data.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pizza_shift_2025.data.dbentity.PizzaEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PizzaDao {
    @Insert
    fun insert(pizza: PizzaEntity)

    @Query("SELECT * FROM pizza")
    fun getBasket():  Flow<List<PizzaEntity>>
}
