package com.example.pc1cosme24100635calderon24100471

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable

@Composable
fun BudgetScreen(navController: NavHostController) {
    var daysInput by remember { mutableStateOf("") }
    var dailyBudgetInput by remember { mutableStateOf("") }
    var accommodationType by remember { mutableStateOf("Estándar") }
    var accommodationFactor by remember { mutableStateOf(1.0f) }
    var resultText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Planificador de Presupuesto", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = daysInput,
            onValueChange = { daysInput = it },
            label = { Text("Cantidad de días") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dailyBudgetInput,
            onValueChange = { dailyBudgetInput = it },
            label = { Text("Presupuesto diario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box {
            Button(onClick = { expanded = true }) { Text("Alojamiento: $accommodationType ($accommodationFactor)") }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("Económico (0.8)") }, onClick = { accommodationType = "Económico"; accommodationFactor = 0.8f; expanded = false })
                DropdownMenuItem(text = { Text("Estándar (1.0)") }, onClick = { accommodationType = "Estándar"; accommodationFactor = 1.0f; expanded = false })
                DropdownMenuItem(text = { Text("Premium (1.5)") }, onClick = { accommodationType = "Premium"; accommodationFactor = 1.5f; expanded = false })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val days = daysInput.toIntOrNull()
                val dailyBudget = dailyBudgetInput.toFloatOrNull()

                if (days == null || days <= 0 || dailyBudget == null || dailyBudget <= 0) {
                    resultText = "Error: Todos los campos son obligatorios y mayores a cero."
                    return@Button
                }

                val total = days * dailyBudget * accommodationFactor
                resultText = "Presupuesto Total: $${String.format("%.2f", total)}\nEscenario: Viaje de tipo $accommodationType por $days días."
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Presupuesto")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(resultText, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.popBackStack() }) { Text("Volver") }
    }
}