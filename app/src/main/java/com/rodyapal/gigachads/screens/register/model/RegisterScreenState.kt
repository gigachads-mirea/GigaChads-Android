package com.rodyapal.gigachads.screens.register.model

import com.rodyapal.gigachads.utils.TextFieldState
import com.rodyapal.gigachads.utils.isEmailValid
import com.rodyapal.gigachads.utils.isPasswordValid

data class RegisterScreenState(
	val email: String,
	val username: String,
	val password: String,
	val confirm: String,
	val emailState: TextFieldState,
	val usernameState: TextFieldState,
	val passwordState: TextFieldState,
	val confirmState: TextFieldState,
	val isPasswordVisible: Boolean,
	val isConfirmVisible: Boolean,
	val isError: Boolean = false,
	val showSuccessMessage: Boolean = false
) {

	fun isValid(): Boolean {
		return isEmailValid(email)
				&& username.isNotBlank()
				&& isPasswordValid(password)
				&& password == confirm
				&& emailState.isValid()
				&& usernameState.isValid()
				&& passwordState.isValid()
				&& confirmState.isValid()
				&& !isError
	}
	companion object {
		fun blank() = RegisterScreenState(
			email = "",
			username = "",
			password = "",
			confirm = "",
			emailState = TextFieldState.UNTOUCHED,
			usernameState = TextFieldState.UNTOUCHED,
			passwordState = TextFieldState.UNTOUCHED,
			confirmState = TextFieldState.UNTOUCHED,
			isPasswordVisible = false,
			isConfirmVisible = false
		)
	}
}
