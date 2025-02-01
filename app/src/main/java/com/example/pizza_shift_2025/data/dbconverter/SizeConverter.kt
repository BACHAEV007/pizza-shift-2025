package com.example.pizza_shift_2025.data.dbconverter

import androidx.room.TypeConverter
import com.example.pizza_shift_2025.data.dbentity.SizeEntity
import com.google.gson.Gson

class SizeConverter {
    @TypeConverter
    fun fromSize(size: SizeEntity): String {
        return Gson().toJson(size)
    }

    @TypeConverter
    fun toSize(data: String): SizeEntity {
        return Gson().fromJson(data, SizeEntity::class.java)
    }
}