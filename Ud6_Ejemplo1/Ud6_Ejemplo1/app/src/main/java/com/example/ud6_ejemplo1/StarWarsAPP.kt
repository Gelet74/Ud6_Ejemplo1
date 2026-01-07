package com.example.ud6_ejemplo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ud6_ejemplo1.ui.StarwarsViewModel


// ---------- ENUM DE PANTALLAS ----------
enum class Pantallas(val titulo: String) {
    MainActivity("STAR WARS"),
    ListadoPersonajes(titulo = "PERSONAJES"),
    ListadoNaves(titulo = "NAVES")
}
@Composable
fun StarWarsApp(
    viewModel: StarwarsViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()
    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.MainActivity.name
    )

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->



        // --- NAVHOST ---
        NavHost(
            navController = navController,
            startDestination = Pantallas.MainActivity.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // -------- PANTALLA MAIN ACTIVITY --------
            composable(Pantallas.MainActivity.name) {
                Inicio(
                    onBotonAceptarPulsado = {
                        navController.navigate(Pantallas.MainActivity.name)
                    },
                )
            }

            // -------- PANTALLA LISTAR PERSONAJES --------
            composable(Pantallas.ListadoPersonajes.name) {
                ListadoPersonajes(
                    viewModel = viewModel,
                    onBotonInicioPulsado = {
                        navController.popBackStack(Pantallas.MainActivity.name, inclusive = false)
                    }
                )
            }
            // -------- PANTALLA LISTAR NAVES--------
            composable(Pantallas.ListadoNaves.name) {
                ListadoNaves(
                    viewModel = viewModel,
                    onBotonInicioPulsado = {
                        navController.popBackStack(Pantallas.MainActivity.name, inclusive = false)
                    }
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text =  pantallaActual.titulo) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "ATRAS")                }
            }
        },
        modifier = Modifier.height(80.dp)
    )
}