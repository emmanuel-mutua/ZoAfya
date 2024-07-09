package com.example.zoafya.ui.auth

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen(route = "welcome")
    data object Login : AuthScreen(route = "login")
    data object ForgotPassword : AuthScreen(route = "forgot_password")
    data object Register : AuthScreen(route = "register")
    data object CustomerHome : AuthScreen(route = "customer")
}
