package com.example.zoafya.ui.auth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zoafya.R
import com.example.zoafya.ui.components.MyOutlinedTextField
import com.example.zoafya.ui.components.PassWordField
import com.example.zoafya.utils.Contants.Customer
import com.example.zoafya.utils.Contants.SProvider
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    onSuccessRegistration: () -> Unit,
    onGotoLoginClicked: () -> Unit,
) {
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPassWordError by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authState by viewModel.authState.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.authEventResponse.collectLatest { response ->
            when (response) {
                is AuthEventResponse.Failure -> {
                    showToast(context, response.message)
                }
                AuthEventResponse.Success -> {
                    showToast(context, "Account created, please login")
                    onSuccessRegistration()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            TopAppBar(title = { Text(text = "Register an account") },
                navigationIcon = {
                    IconButton(
                        onClick =
                        navigateBack
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Signup Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.extraSmall)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            MyOutlinedTextField(
                value = username,
                placeHolder = "Username",
                onValueChange = { username = it },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            )

            MyOutlinedTextField(
                value = phoneNumber,
                placeHolder = "Phone Number",
                onValueChange = { phoneNumber = it },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            PassWordField(
                isPasswordVisible = passwordVisible,
                passwordValue = password,
                label = "Password",
                isError = false,
                onValueChange = { password = it },
            )
            PassWordField(
                isPasswordVisible = passwordVisible,
                passwordValue = confirmPassword,
                label = "ConfirmPassword",
                isError = isPassWordError,
                onValueChange = {
                    confirmPassword = it
                    isPassWordError = confirmPassword != password
                },
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if ( username == "" || phoneNumber == "") {
                        Toast.makeText(context, "All fields required", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }
                    if (isPassWordError) {
                        Toast.makeText(context, "Password Mismatch", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }
                    viewModel.saveUserDetails(
                        username,
                        phoneNumber,
                        password
                    )
                },
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            ) {
                if (!authState.isLoading) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "Register",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                } else {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                }
            }
            TextButton(
                onClick = onGotoLoginClicked,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(),
                        ) {
                            append("Already have an account?")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            ),
                        ) {
                            append("Login")
                        }
                    },
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

