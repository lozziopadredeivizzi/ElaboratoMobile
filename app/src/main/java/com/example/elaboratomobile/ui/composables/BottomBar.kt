package com.example.elaboratomobile.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.elaboratomobile.ui.BookShareRoute

data class BottomBarIcon(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val nextRoute: BookShareRoute
)


@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: BookShareRoute
) {
    if (!BookShareRoute.noBottomBar.contains(currentRoute)) {
        val items = listOf(
            BottomBarIcon(
                title = "Libri",
                selectedIcon = Icons.Filled.MenuBook,
                unselectedIcon = Icons.Outlined.MenuBook,
                nextRoute = BookShareRoute.HomeBooks // Mettere la rotta a cui devo andare quindi libri
            ), BottomBarIcon(
                title = "Eventi",
                selectedIcon = Icons.Filled.CalendarMonth,
                unselectedIcon = Icons.Outlined.CalendarMonth,
                nextRoute = BookShareRoute.Events // Mettere la rotta a cui devo andare quindi Eventi
            ), BottomBarIcon(
                title = "Mappa",
                selectedIcon = Icons.Filled.LocationOn,
                unselectedIcon = Icons.Outlined.LocationOn,
                nextRoute = BookShareRoute.Map // Mettere la rotta a cui devo andare quindi mappa
            ), BottomBarIcon(
                title = "Profilo",
                selectedIcon = Icons.Filled.AccountBox,
                unselectedIcon = Icons.Outlined.AccountBox,
                nextRoute = BookShareRoute.Profile // Mettere la rotta a cui devo andare quindi profilo
            )
        )
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute.title == item.title,
                    onClick = {
                        if (currentRoute != item.nextRoute) {
                            navController.navigate(item.nextRoute.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (currentRoute.title == item.title) item.selectedIcon else item.unselectedIcon,
                            contentDescription = "${item.title} Icon"
                        )
                    },
                    label = { Text(text = item.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White, // Colore dell'icona selezionata
                        unselectedIconColor = Color.Black, // Colore dell'icona non selezionata
                        selectedTextColor = Color.White, // Colore del testo selezionato
                        unselectedTextColor = Color.Black, // Colore del testo non selezionato
                        indicatorColor = MaterialTheme.colorScheme.secondary // Colore dell'indicatore (cerchio selettore)
                    )
                )
            }
        }
    }
}