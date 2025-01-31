package com.example.pizza_shift_2025.data.dbconverter

import androidx.room.TypeConverter
import com.example.pizza_shift_2025.domain.entity.Dough
import com.google.gson.Gson

class DoughConverter {
    @TypeConverter
    fun fromDough(value: Dough): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toDough(value: String): Dough {
        return Gson().fromJson(value, Dough::class.java)
    }
}