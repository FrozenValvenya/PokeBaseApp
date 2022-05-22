package ru.frozenpriest.pokebase.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.presentation.NavigationDestination
import ru.frozenpriest.pokebase.presentation.theme.LoginBackground
import ru.frozenpriest.pokebase.presentation.theme.StatBackground
import ru.frozenpriest.pokebase.presentation.theme.StatBad
import ru.frozenpriest.pokebase.presentation.theme.StatGood

@Composable
fun LoginRegisterScreen(navController: NavController, viewModel: LoginRegisterViewModel) {
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val token by viewModel.token.observeAsState()

    LaunchedEffect(key1 = token) {
        if (token != null) {
            navController.navigate(NavigationDestination.OwnedPokemons.destination) {
                popUpTo(0)
            }
        }
    }

    val channel by viewModel.errorFlow.collectAsState(Errors.None)
    val scaffoldState = rememberScaffoldState()

    val loginError = stringResource(id = R.string.error_logging_in)
    val registerError = stringResource(id = R.string.error_register)

    LaunchedEffect(key1 = channel) {
        when (channel) {
            Errors.Login -> scaffoldState.snackbarHostState.showSnackbar(loginError)
            Errors.Register -> scaffoldState.snackbarHostState.showSnackbar(registerError)
            Errors.None -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LoginBackground)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                backgroundColor = StatBackground
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = {
                            Text(text = stringResource(id = R.string.login))
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(text = stringResource(id = R.string.password))
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = {
                            viewModel.login(login, password)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = StatGood)
                    ) {
                        Text(text = stringResource(id = R.string.log_in))
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = {
                            viewModel.register(login, password)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = StatBad)
                    ) {
                        Text(text = stringResource(id = R.string.register))
                    }
                }
            }
        }
    }
}
