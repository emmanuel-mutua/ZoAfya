package com.example.zoafya.ui.user.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.zoafya.ui.user.profile.ContactScreen
import com.example.zoafya.ui.auth.AuthViewModel
import com.example.zoafya.ui.components.DisplayAlertDialog
import com.example.zoafya.ui.user.chatbot.Chatbot
import com.example.zoafya.ui.user.navigation.BottomNavigationWithBackStack
import com.example.zoafya.ui.user.navigation.CustomerHomeDestinations
import com.example.zoafya.ui.user.profile.ProfileViewModel
import com.example.zoafya.ui.user.services.ServicesScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerHomeScreen(
    navigateToLogin: () -> Unit,
) {
    val navController = rememberNavController()
    val viewModel: AuthViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute == CustomerHomeDestinations.Home.route ||
                currentRoute == CustomerHomeDestinations.Services.route ||
                currentRoute == CustomerHomeDestinations.Chatbot.route ||
                currentRoute == CustomerHomeDestinations.Contact.route
            ) {
                BottomNavigationWithBackStack(navController = navController)
            }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = CustomerHomeDestinations.Home.route,
            ) {
                homeContent(
                )
                services()
                chatbot()
                contact(
                    scope = scope,
                    navigateToLogin = navigateToLogin,
                    viewModel = viewModel,
                )
            }
        },
    )
}

fun NavGraphBuilder.homeContent(

) {
    composable(route = CustomerHomeDestinations.Home.route) {
        val viewModel: CustomerHomeVm = hiltViewModel()
        CustomerHomeScreen(
        )
    }
}


fun NavGraphBuilder.contact(
    scope: CoroutineScope,
    viewModel: AuthViewModel,
    navigateToLogin: () -> Unit,
) {
    composable(route = CustomerHomeDestinations.Contact.route) {
        var dialogOpened by remember { mutableStateOf(false) }
        val profileViewModel: ProfileViewModel = hiltViewModel()
        ContactScreen(
            onSignOutClicked = {
                dialogOpened = true
            }
        )
        DisplayAlertDialog(
            title = "Sign Out",
            message = "Are you sure you want to sign out and exit?",
            dialogOpened = dialogOpened,
            onCloseDialog = {
                dialogOpened = false
            },
            onYesClicked = {
                scope.launch {
                    navigateToLogin()
                }
            },
        )
    }
}

fun NavGraphBuilder.services() {
    composable(CustomerHomeDestinations.Services.route) {
        ServicesScreen()
    }
}

fun NavGraphBuilder.chatbot() {
    composable(CustomerHomeDestinations.Chatbot.route) {
        Chatbot()
    }
}
