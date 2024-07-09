package com.example.zoafya.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
) : ViewModel() {


    private var _authState = MutableStateFlow(AuthStateData())
    val authState = _authState.asStateFlow()


    private var _authEventResponse = Channel<AuthEventResponse>()
    val authEventResponse = _authEventResponse.receiveAsFlow()


    fun signInEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            updateLoading(true)
            _authEventResponse.send(AuthEventResponse.Success)

            updateLoading(false)
        }
    }

    private fun signUpEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            //update state to loading
            delay(3000)
            onAuthSuccess()
        }
    }


    private fun onAuthSuccess() {
        updateLoading(false)
        viewModelScope.launch {
            _authEventResponse.send(AuthEventResponse.Success)
        }
    }

    private fun onAuthError(errorMessage: String) {
        updateLoading(false)
        viewModelScope.launch {
            _authEventResponse.send(AuthEventResponse.Failure(errorMessage))
        }
    }


    fun updateLoading(loading: Boolean) {
        _authState.update {
            it.copy(
                isLoading = loading
            )
        }
    }

    fun saveUserDetails(fullName: String, phoneNumber: String, password: String) {
        signUpEmailAndPassword(fullName, password)
    }
}

sealed class AuthEventResponse {
    data object Success : AuthEventResponse()
    data class Failure(val message: String) : AuthEventResponse()
}

data class AuthStateData(
    val isLoading: Boolean = false,
    val role: String = "",
    val resetPassResult: Boolean = false,
)

