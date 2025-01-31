package com.example.pizza_shift_2025.data.dbentity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pizza_shift_2025.data.dbconverter.DoughConverter
import com.example.pizza_shift_2025.data.dbconverter.PizzaConverter
import com.example.pizza_shift_2025.data.dbconverter.SizeConverter

@Entity(tableName = "pizza")
data class PizzaEntity(
    @PrimaryKey val id: String,
    val name: String,
    val price: Int,
    val img: String,
    val description: String,
    @TypeConverters(PizzaConverter::class)
    val toppings: List<ToppingEntity>,
    @TypeConverters(SizeConverter::class)
    val size: SizeEntity,
    @TypeConverters(DoughConverter::class)
    val doughs: DoughEntity
)