package com.example.pizza_shift_2025.data.network

import com.example.pizza_shift_2025.data.model.PizzasResponseModel
import retrofit2.http.GET

interface PizzaApi {
    @GET("pizza/catalog")
    suspend fun getPizzaCatalog(): PizzasResponseModel
}