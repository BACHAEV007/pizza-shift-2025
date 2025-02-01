package com.example.pizza_shift_2025.data.network

import com.example.pizza_shift_2025.data.model.PizzasResponseModel
import com.example.pizza_shift_2025.domain.entity.PizzaPayment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PizzaApi {
    @GET("pizza/catalog")
    suspend fun getPizzaCatalog(): PizzasResponseModel

    @POST("pizza/order")
    suspend fun orderPizza(
        @Body pizzaOrder: PizzaPayment
    )
}