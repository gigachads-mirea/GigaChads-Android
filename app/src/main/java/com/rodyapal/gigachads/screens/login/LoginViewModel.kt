package com.rodyapal.gigachads.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.login.model.LoginScreenEvent
import com.rodyapal.gigachads.screens.login.model.LoginScreenState
import com.rodyapal.gigachads.utils.EMAIL_REGEX_PATTERN
import com.rodyapal.gigachads.utils.PASSWORD_REGEX_PATTERN
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

	override fun reduce(event: LoginScreenEvent) = _viewState.update {
		when (event) {
			is LoginScreenEvent.OnEmailInput -> it.copy(
				email = event.input,
				emailState = if (event.input.isBlank()) TextFieldState.UNTOUCHED else {
					if (isEmailValid(event.input)) TextFieldState.VALID
					else TextFieldState.INVALID
				}
			)
			is LoginScreenEvent.OnPasswordInput -> it.copy(
				password = event.input,
				passwordState = if (event.input.isBlank()) TextFieldState.UNTOUCHED else {
					if (isPasswordValid(event.input)) TextFieldState.VALID
					else TextFieldState.INVALID
				}
			)
			is LoginScreenEvent.OnPasswordVisibilityClick -> it.copy(
				isPasswordVisible = !it.isPasswordVisible,
			)
			is LoginScreenEvent.OnLoginClick -> onLogin(it, event.navigate)
			is LoginScreenEvent.OnLoginEnd -> {
				event.navigate()
				LoginScreenState.blank()
			}
			is LoginScreenEvent.OnLoginError -> it.copy(
				isLoginError = true,
				isLoginInProgress = false
			)
		}
	}

	private fun onLogin(current: LoginScreenState, navigate: () -> Unit): LoginScreenState = with(current) {
		if (isValid()) {
			viewModelScope.launch {
				val isSuccess = userRepository.auth(email, password)
				if (isSuccess) {
					reduce(LoginScreenEvent.OnLoginEnd(navigate))
				} else {
					reduce(LoginScreenEvent.OnLoginError)
				}
			}
			copy(
				isLoginInProgress = true,
				isLoginError = false
			)
		} else {
			current
		}
	}
}