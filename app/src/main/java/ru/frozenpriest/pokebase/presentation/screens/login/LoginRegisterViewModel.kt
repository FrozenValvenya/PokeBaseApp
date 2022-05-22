package ru.frozenpriest.pokebase.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCase
import timber.log.Timber
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor(
    private val loginRegisterUseCase: LoginRegisterUseCase
) : ViewModel() {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token

    private val errorChannel = Channel<Errors>()
    val errorFlow = errorChannel.receiveAsFlow()

    fun login(login: String, password: String) = viewModelScope.launch {
        if (login.isBlank() || password.isBlank()) return@launch

        val token = loginRegisterUseCase.login(login, password)
        token.onSuccess {
            Timber.d("Got token: $it")
            _token.value = it
        }
        token.onFailure {
            Timber.d("Got error: $it")
            errorChannel.send(Errors.Login)
        }
    }

    fun register(login: String, password: String) = viewModelScope.launch {
        if (login.isBlank() || password.isBlank()) return@launch

        val token = loginRegisterUseCase.register(login, password)
        token.onSuccess {
            Timber.d("Got token: $it")
            _token.value = it
        }
        token.onFailure {
            Timber.d("Got error: $it")
            errorChannel.send(Errors.Register)
        }
    }
}

enum class Errors {
    Login, Register, None
}
