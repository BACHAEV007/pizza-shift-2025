package com.example.pizza_shift_2025.di

import com.example.pizza_shift_2025.data.mapper.PizzaMapper
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.data.repository.BasketRepositoryImpl
import com.example.pizza_shift_2025.data.repository.PizzaRepositoryImpl
import com.example.pizza_shift_2025.domain.repository.BasketRepository
import com.example.pizza_shift_2025.domain.repository.PizzaRepository
import com.example.pizza_shift_2025.domain.usecase.AddPizzaToBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.domain.usecase.OrderPizzaUseCase
import com.example.pizza_shift_2025.presentation.viewmodel.BasketViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.CheckoutViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val module = module {
    factoryOf(::BasketRepositoryImpl) bind BasketRepository::class
    factoryOf(::PizzaRepositoryImpl) bind PizzaRepository::class
    single { get<Retrofit>().create(PizzaApi::class.java)}
    factoryOf(::AddPizzaToBasketUseCase)
    factoryOf(::CountPizzaPriceUseCase)
    factoryOf(::GetBasketUseCase)
    factoryOf(::GetPizzaCatalogUseCase)
    factoryOf(::OrderPizzaUseCase)
    factoryOf(::PizzaMapper)

    viewModelOf(::BasketViewModel)
    viewModelOf(::CheckoutViewModel)
    viewModelOf(::PizzaCatalogViewModel)
    viewModelOf(::PizzaDetailViewModel)
}
