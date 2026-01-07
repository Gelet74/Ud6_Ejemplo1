package com.example.ud6_ejemplo1.conexion

import com.example.ud6_ejemplo1.modelo.Respuesta
import com.example.ud6_ejemplo1.modelo.Respuesta2
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://swapi.dev/api/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ServicioApi {
    @GET("people")
    suspend fun obtenerPersonaje(): Respuesta

    @GET("starships")
    suspend fun obtenerNaves(): Respuesta2
}
object Api {
    val servicioRetrofit: ServicioApi by lazy {
        retrofit.create(ServicioApi::class.java)
    }
}