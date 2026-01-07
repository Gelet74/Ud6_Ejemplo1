package com.example.ud6_ejemplo1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ud6_ejemplo1.conexion.Api
import com.example.ud6_ejemplo1.modelo.Respuesta
import com.example.ud6_ejemplo1.modelo.Respuesta2
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StarwarsUIState {
    data class Exito(val respuesta: Respuesta) : StarwarsUIState
    data class Exito2(val respuesta2: Respuesta2) : StarwarsUIState
    object Error : StarwarsUIState
    object Cargando : StarwarsUIState
}

class StarwarsViewModel : ViewModel() {

    private val _starwarsUIState = MutableStateFlow<StarwarsUIState>(StarwarsUIState.Cargando)
    val starwarsUIState: StateFlow<StarwarsUIState> = _starwarsUIState

    init {
        obtenerNaves()
    }

    fun obtenerPersonaje() {
        viewModelScope.launch {
            _starwarsUIState.value = StarwarsUIState.Cargando
            _starwarsUIState.value = try {
                val listaPersonajes = Api.servicioRetrofit.obtenerPersonaje()
                StarwarsUIState.Exito(listaPersonajes)
            } catch (e: IOException) {
                StarwarsUIState.Error
            } catch (e: HttpException) {
                StarwarsUIState.Error
            }
        }
    }

    fun obtenerNaves() {
        viewModelScope.launch {
            _starwarsUIState.value = StarwarsUIState.Cargando
            _starwarsUIState.value = try {
                val listaNaves = Api.servicioRetrofit.obtenerNaves()
                StarwarsUIState.Exito2(listaNaves)
            } catch (e: IOException) {
                StarwarsUIState.Error
            } catch (e: HttpException) {
                StarwarsUIState.Error
            }
        }
    }
}

