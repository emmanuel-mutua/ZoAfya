package com.example.zoafya.ui.user.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zoafya.ui.user.home.CustomerHomeVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val viewModel : CustomerHomeVm = hiltViewModel()

    TopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {

        },
        title = {
            Text(text = "ZoAfya", color = MaterialTheme.colorScheme.primary)
        },
        actions = {
        },
    )
}
