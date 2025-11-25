package com.example.ud6_ejemplo1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var starwarsUIState: StarwarsUIState by mutableStateOf(StarwarsUIState.Cargando)
        private set

    init {
        obtenerNaves()
    }

    fun obtenerPersonaje() {
        viewModelScope.launch {
            starwarsUIState = StarwarsUIState.Cargando
            starwarsUIState = try {
                val listaPersonajes = Api.servicioRetrofit.obtenerPersonaje()
                StarwarsUIState.Exito(listaPersonajes)
            } catch (e: IOException){
                StarwarsUIState.Error
            } catch (e: HttpException){
                StarwarsUIState.Error
            }
        }
    }
    fun obtenerNaves() {
        viewModelScope.launch {
          starwarsUIState = StarwarsUIState.Cargando
            starwarsUIState = try {
                val listaNaves = Api.servicioRetrofit.obtenerNaves()
                StarwarsUIState.Exito2(listaNaves)
            } catch (e: IOException){
                StarwarsUIState.Error
            }catch (e: HttpException){
                StarwarsUIState.Error
            }
        }
    }
}