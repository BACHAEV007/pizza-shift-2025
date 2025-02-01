package com.example.pizza_shift_2025.data.dbconverter

import androidx.room.TypeConverter
import com.example.pizza_shift_2025.data.dbentity.DoughEntity
import com.example.pizza_shift_2025.domain.entity.Dough
import com.google.gson.Gson

class DoughConverter {
    @TypeConverter
    fun fromDough(dough: DoughEntity): String {
        return Gson().toJson(dough)
    }

    @TypeConverter
    fun toDough(data: String): DoughEntity {
        return Gson().fromJson(data, DoughEntity::class.java)
    }
}