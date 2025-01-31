package com.example.pizza_shift_2025.data.dbconverter

import androidx.room.TypeConverter
import com.example.pizza_shift_2025.data.dbentity.ToppingEntity
import com.example.pizza_shift_2025.domain.entity.Topping
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PizzaConverter {
    @TypeConverter
    fun fromToppingList(value: List<ToppingEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toToppingList(value: String): List<ToppingEntity> {
        val listType = object : TypeToken<List<ToppingEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}