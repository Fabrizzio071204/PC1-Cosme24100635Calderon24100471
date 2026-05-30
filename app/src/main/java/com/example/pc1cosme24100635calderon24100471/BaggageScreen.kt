package com.example.pc1cosme24100635calderon24100471

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BaggageScreen(navController: NavHostController) {
    var weightInput by remember { mutableStateOf("") }
    var flightType by remember { mutableStateOf("Nacional") } // O DropdownMenu
    var resultText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
//pruebacomentario
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Calculadora de Equipaje", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it; errorMessage = "" },
            label = { Text("Peso de la maleta (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage.isNotEmpty()
        )
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selector Dropdown para el tipo de vuelo
        Box {
            Button(onClick = { expanded = true }) { Text("Tipo de Vuelo: $flightType") }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("Nacional (Máx 23 kg)") }, onClick = { flightType = "Nacional"; expanded = false })
                DropdownMenuItem(text = { Text("Internacional (Máx 32 kg)") }, onClick = { flightType = "Internacional"; expanded = false })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val weight = weightInput.toFloatOrNull()
                // Validaciones solicitadas
                if (weightInput.isBlank()) { errorMessage = "Campo obligatorio"; return@Button }
                if (weight == null) { errorMessage = "Valor numérico inválido"; return@Button }
                if (weight <= 0) { errorMessage = "Debe ser mayor a cero"; return@Button }

                val maxAllowed = if (flightType == "Nacional") 23f else 32f
                if (weight <= maxAllowed) {
                    resultText = "Cumple el límite permitido."
                } else {
                    val excess = weight - maxAllowed
                    resultText = "Excede el límite permitido por ${String.format("%.2f", excess)} kg."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(resultText, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.popBackStack() }) { Text("Volver") }
    }
}