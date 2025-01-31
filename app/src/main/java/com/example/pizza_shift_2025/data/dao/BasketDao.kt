package com.example.pizza_shift_2025.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.pizza_shift_2025.data.dbentity.PizzaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {
    @Insert
    suspend fun insert(pizza: PizzaEntity)

    @Query("SELECT * FROM pizza")
    suspend fun getAllPizzas(): List<PizzaEntity>
}