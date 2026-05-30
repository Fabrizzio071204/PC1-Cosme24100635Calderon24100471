package com.example.pc1cosme24100635calderon24100471

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage

data class Destination(
    val country: String,
    val city: String,
    val averageCost: Double,
    val imageUrl: String
)

@Composable
fun CatalogScreen(navController: NavHostController) {
    val destinations = remember {
        listOf(
            Destination("Perú", "Cusco", 450.0, "https://images.unsplash.com/photo-1509024644558-2f56ce76c490?w=400"),
            Destination("Francia", "París", 1200.0, "https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=400"),
            Destination("Japón", "Tokio", 1500.0, "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=400"),
            Destination("Italia", "Roma", 950.0, "https://images.unsplash.com/photo-1552832230-c0197dd311b5?w=400"),
            Destination("México", "Cancún", 700.0, "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=400")
        )
    }

    val totalCost = destinations.sumOf { it.averageCost }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Catálogo de Destinos", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(destinations) { destination ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            model = destination.imageUrl,
                            contentDescription = destination.city,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 8.dp)
                        )
                        Column {
                            Text("País: ${destination.country}", style = MaterialTheme.typography.bodyMedium)
                            Text("Ciudad: ${destination.city}", style = MaterialTheme.typography.bodyLarge)
                            Text("Costo promedio: $${destination.averageCost}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cantidad total de destinos: ${destinations.size}", style = MaterialTheme.typography.titleMedium)
                Text("Suma acumulada de costos: $${String.format("%.2f", totalCost)}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Button(onClick = { navController.popBackStack() }) { Text("Volver") }
    }
}