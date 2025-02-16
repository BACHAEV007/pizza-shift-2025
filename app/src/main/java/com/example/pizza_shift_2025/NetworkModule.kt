package com.example.pizza_shift_2025


import com.example.pizza_shift_2025.NetworkModule.Companion.BASE_URL
import com.example.pizza_shift_2025.NetworkModule.Companion.CONNECT_TIMEOUT
import com.example.pizza_shift_2025.NetworkModule.Companion.READ_TIMEOUT
import com.example.pizza_shift_2025.NetworkModule.Companion.WRITE_TIMEOUT
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(get<Converter.Factory>())
            .build()
    }

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
    }
}
class NetworkModule {

    internal companion object {

        const val BASE_URL = "https://shift-intensive.ru/api/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }

    val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(provideKotlinXSerializationFactory())
        .build()

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .build()

    private fun provideKotlinXSerializationFactory(): Converter.Factory =
        Json.asConverterFactory("application/json; charset=UTF8".toMediaType())

    private fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}