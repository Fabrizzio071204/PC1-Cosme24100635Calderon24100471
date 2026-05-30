package com.example.pc1cosme24100635calderon24100471

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "menu",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("menu") { MenuScreen(navController) }
                        composable("equipaje") { Text("Pantalla Equipaje - Próximamente") }
                        composable("presupuesto") { Text("Pantalla Presupuesto - Próximamente") }
                        composable("catalogo") { Text("Pantalla Catálogo - Próximamente") }
                        composable("permiso") { Text("Pantalla Permiso - Próximamente") }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Travel Companion App", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("equipaje") }) { Text("Calculadora de Equipaje") }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("presupuesto") }) { Text("Planificador de Presupuesto") }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("catalogo") }) { Text("Catálogo de Destinos") }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("permiso") }) { Text("Permiso de Ubicación") }
    }
}