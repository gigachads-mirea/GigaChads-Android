package com.rodyapal.gigachads.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.login.model.LoginScreenEvent
import com.rodyapal.gigachads.screens.login.model.LoginScreenState
import com.rodyapal.gigachads.utils.TextFieldState
import com.rodyapal.gigachads.utils.isEmailValid
import com.rodyapal.gigachads.utils.isPasswordValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
	private val userRepository: UserRepository
) : ViewModel(), Reducer<LoginScreenEvent> {

	private val _viewState = MutableStateFlow(LoginScreenState.blank())
	val viewState = _viewState as StateFlow<LoginScreenState>

	override fun reduce(event: LoginScreenEvent) {
		when (event) {
			is LoginScreenEvent.OnEmailInput -> _viewState.update {
				it.copy(
					email = event.input,
					emailState = if (event.input.isBlank()) TextFieldState.UNTOUCHED else {
						if (isEmailValid(event.input)) TextFieldState.VALID
						else TextFieldState.INVALID
					}
				)
			}

			is LoginScreenEvent.OnPasswordInput -> _viewState.update {
				it.copy(
					password = event.input,
					passwordState = if (event.input.isBlank()) TextFieldState.UNTOUCHED else {
						if (isPasswordValid(event.input)) TextFieldState.VALID
						else TextFieldState.INVALID
					}
				)
			}

			is LoginScreenEvent.OnPasswordVisibilityClick -> _viewState.update {
				it.copy(
					isPasswordVisible = !it.isPasswordVisible,
				)
			}

			is LoginScreenEvent.OnLoginClick -> if (viewState.value.isValid()) {
				_viewState.update {
					it.copy(
						isLoginInProgress = true,
						isLoginError = false
					)
				}
				onLogin(viewState.value, event.navigate)
			}

			is LoginScreenEvent.OnLoginEnd -> {
				event.navigate()
				_viewState.update { LoginScreenState.blank() }
			}

			is LoginScreenEvent.OnLoginError -> _viewState.update {
				it.copy(
					isLoginError = true,
					isLoginInProgress = false
				)
			}

			is LoginScreenEvent.OnLoginErrorEnd -> _viewState.update {
				it.copy(
					isLoginError = false
				)
			}
		}
	}

	private fun onLogin(current: LoginScreenState, navigate: () -> Unit) =
		viewModelScope.launch {
			val isSuccess = userRepository.auth(current.email, current.password)
			if (isSuccess) {
				reduce(LoginScreenEvent.OnLoginEnd(navigate))
			} else {
				reduce(LoginScreenEvent.OnLoginError)
			}
		}
}