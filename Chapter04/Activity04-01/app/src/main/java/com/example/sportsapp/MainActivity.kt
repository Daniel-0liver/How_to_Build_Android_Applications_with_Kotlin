package com.example.sportsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sportsapp.ui.theme.SportsAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsAppTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerHost(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(
                            R.string.app_name
                        )
                    )
                },
                modifier = modifier.statusBarsPadding(),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Destination.Home
        ) {
            composable<Destination.Home> {
                MainScreen(Destination.Home.label)
            }
            composable<Destination.MySports> {
                MySportsScreen(
                    navController = navController
                )
            }
            composable<Destination.Calendar> {
                MainScreen(Destination.Calendar.label)
            }
            composable<Destination.Profile> {
                MainScreen(Destination.Profile.label)
            }
            composable<Destination.SelectedSport> { backStackEntry ->
                val selectedSport = backStackEntry.toRoute<Destination.SelectedSport>()
                SelectedSportScreen(
                    sportName = selectedSport.sportName
                )
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navigationDrawerItems = listOf(
        NavigationDrawer.Home,
        NavigationDrawer.MySports,
        NavigationDrawer.Calendar,
        NavigationDrawer.Profile
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(256.dp)
            ) {
                Box(
                    modifier = Modifier.width(256.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.width(120.dp),
                        painter = painterResource(
                            id = R.drawable.ic_launcher_background
                        ),
                        contentDescription = "Logo"
                    )
                    Image(
                        modifier = Modifier.width(120.dp),
                        painter = painterResource(
                            id = R.drawable.ic_launcher_foreground
                        ),
                        contentDescription = "Logo"
                    )
                }
                navigationDrawerItems.forEach { item ->
                    val isSelected = currentDestination?.hasRoute(
                        item.route::class
                    ) == true
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                item.label
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(
                                    navController.graph.startDestinationId
                                ) {
                                    saveState = true
                                }
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavigationDrawerHost(
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            navController = navController
        )
    }
}