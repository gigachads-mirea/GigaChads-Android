package com.rodyapal.gigachads.screens.login.model

sealed class LoginScreenEvent {
	data class OnEmailInput(
		val input: String
	) : LoginScreenEvent()
	data class OnPasswordInput(
		val input: String
	) : LoginScreenEvent()
	data class OnLoginClick(
		val navigate: () -> Unit
	) : LoginScreenEvent()
	object OnPasswordVisibilityClick : LoginScreenEvent()
	data class OnLoginEnd(
		val navigate: () -> Unit
	) : LoginScreenEvent()
	object OnLoginError : LoginScreenEvent()
}
