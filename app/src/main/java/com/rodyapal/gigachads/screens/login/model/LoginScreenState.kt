package com.rodyapal.gigachads.screens.login.model

import com.rodyapal.gigachads.utils.TextFieldState

data class LoginScreenState(
	val email: String,
	val password: String,
	val emailState: TextFieldState,
	val passwordState: TextFieldState,
	val isPasswordVisible: Boolean,
	val isLoginError: Boolean = false,
	val isLoginInProgress: Boolean = false
) {

	fun isValid(): Boolean = email.isNotBlank() and emailState.isValid() and password.isNotBlank() and passwordState.isValid()
	companion object {
		fun blank() = LoginScreenState(
			email = "",
			password = "",
			emailState = TextFieldState.UNTOUCHED,
			passwordState = TextFieldState.UNTOUCHED,
			isPasswordVisible = false,
			isLoginError = false,
			isLoginInProgress = false
		)
	}
}