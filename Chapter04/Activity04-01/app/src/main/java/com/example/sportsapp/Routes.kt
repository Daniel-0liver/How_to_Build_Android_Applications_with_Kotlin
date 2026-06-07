package com.example.sportsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Sports
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val label: String) {
    @Serializable
    data object Home : Destination("Home")

    @Serializable
    data object MySports : Destination("My Sports")

    @Serializable
    data object Calendar : Destination("Calendar")

    @Serializable
    data object Profile : Destination("Profile")

    @Serializable
    data class SelectedSport(val sportName: String) : Destination(sportName)
}

sealed class NavigationDrawer(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Destination,
) {
    data object Home : NavigationDrawer(
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = Destination.Home
    )

    data object MySports : NavigationDrawer(
        label = "My Sports",
        selectedIcon = Icons.Filled.Sports,
        unselectedIcon = Icons.Outlined.Sports,
        route = Destination.MySports
    )

    data object Calendar : NavigationDrawer(
        label = "Calendar",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        route = Destination.Calendar
    )

    data object Profile : NavigationDrawer(
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        route = Destination.Profile
    )
}