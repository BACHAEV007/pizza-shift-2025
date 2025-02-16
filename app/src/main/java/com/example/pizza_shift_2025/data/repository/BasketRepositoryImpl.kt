package com.example.pizza_shift_2025.data.repository

import android.util.Log
import com.example.pizza_shift_2025.AppContext
import com.example.pizza_shift_2025.data.database.PizzaDataBase
import com.example.pizza_shift_2025.data.mapper.PizzaMapper
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BasketRepositoryImpl (
    private val pizzaApi: PizzaApi,
    private val mapper: PizzaMapper,
    private val pizzaDataBase: PizzaDataBase
) : BasketRepository{
    override suspend fun addPizzaToBasket(pizza: BasketPizza) {
        withContext(Dispatchers.IO) {
            pizzaDataBase.pizzaDao().insert(mapper.toPizzaEntity(pizza))
        }
    }
    override fun getBasket(): Flow<List<BasketPizza>> {
        return pizzaDataBase.pizzaDao().getBasket().map { pizzaEntities ->
            pizzaEntities.map { mapper.toPizza(it) }
        }
    }

}