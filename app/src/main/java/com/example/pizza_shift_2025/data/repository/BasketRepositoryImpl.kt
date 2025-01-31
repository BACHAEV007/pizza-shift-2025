package com.example.pizza_shift_2025.data.repository

import com.example.pizza_shift_2025.AppContext
import com.example.pizza_shift_2025.data.database.PizzaDataBase
import com.example.pizza_shift_2025.data.mapper.PizzaMapper
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.BasketRepository

class BasketRepositoryImpl (
    private val pizzaApi: PizzaApi,
    private val mapper: PizzaMapper
) : BasketRepository{
    val db = PizzaDataBase.getDatabase(AppContext.context)
    override suspend fun addPizzaToBasket(pizza: Pizza) =
        db.PizzaDao().insert(mapper.toPizzaEntity(pizza))

    override suspend fun getBasket(): List<Pizza> {
        TODO("Not yet implemented")
    }

}