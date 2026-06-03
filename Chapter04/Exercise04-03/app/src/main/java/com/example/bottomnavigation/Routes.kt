package com.example.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val label: String) {
    @Serializable
    data object Home: Destination("Home")
    @Serializable
    data object Shopping: Destination("Cart")
    @Serializable
    data object Favorites: Destination("Favorites")
    @Serializable
    data object Calendar: Destination("Calendar")
    @Serializable
    data object Bin: Destination("Bin")
}

sealed class BottomNavigation(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int,
    val route: Destination
) {
    data object Home : BottomNavigation(
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        badgeCount = 0,
        route = Destination.Home
    )
    data object Shopping : BottomNavigation(
        label = "Cart",
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        badgeCount = 0,
        route = Destination.Shopping
    )
    data object Favorites : BottomNavigation(
        label = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        badgeCount = 0,
        route = Destination.Favorites
    )
    data object Calendar : BottomNavigation(
        label = "Calendar",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        badgeCount = 1,
        route = Destination.Calendar
    )
    data object Bin : BottomNavigation(
        label = "Bin",
        selectedIcon = Icons.Filled.Delete,
        unselectedIcon = Icons.Outlined.Delete,
        badgeCount = 0,
        route = Destination.Bin
    )
}