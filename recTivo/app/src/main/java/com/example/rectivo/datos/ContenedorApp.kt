package com.example.rectivo.datos


import com.example.rectivo.conexion.RectivoServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val armarioRepositorio: ArmarioRepositorio
}

class ArmarioContenedorApp : ContenedorApp {
    private val baseUrl = "http://10.0.2.2:3000"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: RectivoServicioApi by lazy {
        retrofit.create(TrabajadoresServicioApi::class.java)
    }

    override val trabajadorRepositorio: TrabajadorRepositorio by lazy {
        ConexionTrabajadorRepositorio(servicioRetrofit)
    }
}