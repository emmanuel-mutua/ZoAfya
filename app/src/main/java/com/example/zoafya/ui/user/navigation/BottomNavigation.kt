package com.example.zoafya.ui.user.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationWithBackStack(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navigationItems: List<CustomerHomeDestinations> = listOf(
        CustomerHomeDestinations.Home,
        CustomerHomeDestinations.Services,
        CustomerHomeDestinations.Chatbot,
        CustomerHomeDestinations.Contact,
    )
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    var previousBottomNav by remember {
        mutableStateOf<CustomerHomeDestinations>(CustomerHomeDestinations.Home)
    }
    val currentlySelectedItem by remember {
        derivedStateOf {
            currentBackStackEntry?.let {
                val route = it.destination.route
                previousBottomNav = when (route) {
                    CustomerHomeDestinations.Home.route -> CustomerHomeDestinations.Home
                    CustomerHomeDestinations.Services.route -> CustomerHomeDestinations.Services
                    CustomerHomeDestinations.Chatbot.route -> CustomerHomeDestinations.Chatbot
                    CustomerHomeDestinations.Contact.route -> CustomerHomeDestinations.Contact
                    else -> previousBottomNav
                }
                previousBottomNav
            } ?: previousBottomNav
        }
    }
    BottomAppBar(
        modifier = modifier
            .padding(top = 3.dp)
            .background(Color.Transparent),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        navigationItems.forEach { screen ->
            NavigationBarItem(
                alwaysShowLabel = true,
                interactionSource = MutableInteractionSource(),
                selected = screen == currentlySelectedItem,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "${screen.label} icon",
                        tint = if (screen == currentlySelectedItem) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        },
                    )
                },
                label = {
                    Text(
                        color = if (screen == currentlySelectedItem) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        },
                        text = screen.label,
                    )
                },
            )
        }
    }
}
