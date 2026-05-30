package com.example.pc1cosme24100635calderon24100471

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PermissionScreen(navController: NavHostController) {
    val context = LocalContext.current

    // Estados requeridos por la guía de la práctica
    var permissionStatus by remember { mutableStateOf("Permiso pendiente de solicitud") }

    // Configuración del launcher para solicitar los dos permisos en tiempo de ejecución
    val locationPermissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        permissionStatus = if (fineGranted || coarseGranted) {
            "Permiso concedido"
        } else {
            "Permiso denegado"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Asistencia de Viaje - Permisos", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
//comentariorandomoño
        // Muestra el texto dinámico según el estado del permiso
        Text("Estado: $permissionStatus", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Lanza la solicitud de ambos permisos en paralelo
            locationPermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }) {
            Text("Solicitar Permisos de Ubicación")
        }

        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { navController.popBackStack() }) { Text("Volver al Menú") }
    }
}