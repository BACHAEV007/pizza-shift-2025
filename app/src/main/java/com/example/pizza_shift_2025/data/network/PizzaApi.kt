package com.example.pizza_shift_2025.data.network

import com.example.pizza_shift_2025.data.model.PizzaModel
import com.example.pizza_shift_2025.domain.entity.Pizza
import retrofit2.http.GET

interface PizzaApi {
    @GET("pizza/catalog")
    suspend fun getPizzaCatalog(): List<PizzaModel>
}