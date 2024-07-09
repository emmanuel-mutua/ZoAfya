package com.example.zoafya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.zoafya.ui.auth.AuthScreen
import com.example.zoafya.ui.auth.AuthViewModel
import com.example.zoafya.data.remote.BookService
import com.example.zoafya.data.remote.dto.PostsResponse
import com.example.zoafya.ui.auth.AuthNavGraph
import com.example.zoafya.ui.theme.KtorForNetworkCallsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val service = BookService.provideHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = produceState<List<PostsResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                })
            KtorForNetworkCallsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ZoeAfyaApp()
                }
            }
        }
    }
}


@Composable
fun ZoeAfyaApp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
    ) {
        val navController = rememberNavController()
        val authViewModel: AuthViewModel = hiltViewModel()
        val startDestination =
//            if (authViewModel.currentUser != null) {
//                AuthScreen.Home.route
//            } else {
                AuthScreen.Welcome.route
//            }
        val registerState = authViewModel.authState.collectAsState().value
        AuthNavGraph(
            startDestination = startDestination,
            navController = navController,
            authViewModel = authViewModel,
            registerState = registerState,
        )
    }
}
