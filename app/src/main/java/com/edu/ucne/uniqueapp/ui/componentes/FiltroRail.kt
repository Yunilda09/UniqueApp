package com.edu.ucne.uniqueapp.ui.componentes

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Apps
import androidx.compose.material.icons.twotone.CleanHands
import androidx.compose.material.icons.twotone.Face2
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.edu.ucne.uniqueapp.R

data class FiltroItem(
    val icon: ImageVector,
    val id: Int,
    val label: String
)

@Composable
fun FiltroRail(onItemClick: (Int) -> Unit ) {
    var itemSeleccionado by remember{ mutableStateOf(0)}
    val items = listOf(
        FiltroItem(Icons.TwoTone.Apps, 0, "Todos"),
        FiltroItem(Icons.TwoTone.Spa, 1,  "Spa"),
        FiltroItem(Icons.TwoTone.Face2, 2,  "Salon"),
        FiltroItem(Icons.TwoTone.CleanHands, 3,  "Nails"),
    )
    NavigationRail(modifier = Modifier.fillMaxHeight()) {
        items.forEachIndexed{ indice, item ->
            NavigationRailItem(
                selected = itemSeleccionado == indice,
                onClick = {
                    itemSeleccionado = indice
                    onItemClick(item.id)
                },
                icon = { Icon(item.icon, null) },
                label = {Text(item.label)}

            )
        }
    }
}