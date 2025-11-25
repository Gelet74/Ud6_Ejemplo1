package com.example.ud6_ejemplo1.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Naves(
    @SerialName(value = "name")
    val nombre: String,
    @SerialName(value = "length")
    val largo: String,
    @SerialName(value = "films")
    val peliculas: List<String>
)