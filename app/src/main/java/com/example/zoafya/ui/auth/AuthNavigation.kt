package com.example.zoafya.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zoafya.ui.user.home.CustomerHomeScreen

@Composable
fun AuthNavGraph(
    startDestination: String,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    registerState: AuthStateData,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        welcomeScreen(
            onGetStartedClick = {
                navController.navigateWithPop(AuthScreen.Register.route)
            },
            onLoginButtonClick = {
                navController.navigateWithPop(AuthScreen.Login.route)
            }
        )
        loginScreen(
            viewModel = authViewModel,
            navigateBack = {
                navController.popBackStack()
            },
            navigateToHome = {
                navController.navigateWithPop(AuthScreen.CustomerHome.route)
            }
        ) {
            navController.navigate(AuthScreen.ForgotPassword.route)
        }
        registerScreen(
            viewModel = authViewModel,
            navigateToLogin = {
                navController.navigateWithPop(AuthScreen.Login.route)
            },
            navigateBack = {
                navController.popBackStack()
            }
        )
        customerHomeScreen(
            navigateToLogin = {
                navController.navigateAndPopHome(AuthScreen.CustomerHome.route)
            },
        )
        forgotPasswordScreen(
            navigateToLogin = {
                navController.popBackStack()
            }
        ) { email ->

        }
    }
}

fun NavGraphBuilder.loginScreen(
    viewModel: AuthViewModel,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    composable(AuthScreen.Login.route) {
        LoginScreen(
            viewModel = viewModel,
            navigateBack = navigateBack,
            navigateToHome = navigateToHome,
            navigateToForgotPassword = navigateToForgotPassword
        )
    }
}

fun NavGraphBuilder.registerScreen(
    viewModel: AuthViewModel,
    navigateToLogin: () -> Unit,
    navigateBack: () -> Unit,
) {
    composable(AuthScreen.Register.route) {
        RegisterScreen(
            viewModel = viewModel,
            navigateBack = navigateBack,
            onSuccessRegistration = {
                navigateToLogin()
            },
            onGotoLoginClicked = {
                navigateToLogin()
            },
        )
    }
}
fun NavGraphBuilder.forgotPasswordScreen(
    navigateToLogin: () -> Unit,
    resetPassword: (String) -> Unit,
    ){
    composable(AuthScreen.ForgotPassword.route){
        ForgotPasswordScreen(
            navigateBack = navigateToLogin,
            resetPassword = resetPassword
        )
    }
}

fun NavGraphBuilder.welcomeScreen(
    onGetStartedClick: () -> Unit,
    onLoginButtonClick: () -> Unit,
) {
    composable(AuthScreen.Welcome.route) {
        WelcomeScreen(
            onGetStartedClick = onGetStartedClick,
            onLoginButtonClick = onLoginButtonClick
        )
    }
}

fun NavGraphBuilder.customerHomeScreen(navigateToLogin: () -> Unit) {
    composable(AuthScreen.CustomerHome.route) {
        CustomerHomeScreen(
            navigateToLogin = navigateToLogin,
        )
    }
}


private fun NavController.navigateWithPop(route: String) {
    navigate(route) {
        popUpTo(AuthScreen.Welcome.route) {
            saveState = true
        }
        restoreState = true
    }
}

private fun NavController.navigateAndPopHome(route:String){
    popBackStack()
    navigate(route){
        popUpTo(AuthScreen.Welcome.route)
    }
}

