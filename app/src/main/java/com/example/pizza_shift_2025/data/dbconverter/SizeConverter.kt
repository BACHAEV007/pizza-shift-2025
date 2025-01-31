package com.example.pizza_shift_2025.data.dbconverter

import androidx.room.TypeConverter
import com.example.pizza_shift_2025.data.dbentity.SizeEntity
import com.google.gson.Gson

class SizeConverter {
    @TypeConverter
    fun fromSize(value: SizeEntity): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSize(value: String): SizeEntity {
        return Gson().fromJson(value, SizeEntity::class.java)
    }
}