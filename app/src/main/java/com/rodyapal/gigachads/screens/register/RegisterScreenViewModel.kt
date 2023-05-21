package com.rodyapal.gigachads.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.register.model.RegisterScreenEvent
import com.rodyapal.gigachads.screens.register.model.RegisterScreenState
import com.rodyapal.gigachads.utils.TextFieldState
import com.rodyapal.gigachads.utils.isEmailValid
import com.rodyapal.gigachads.utils.isPasswordValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterScreenViewModel(
	private val userRepository: UserRepository
) : ViewModel(), Reducer<RegisterScreenEvent> {

	private val _viewState = MutableStateFlow(RegisterScreenState.blank())
	val viewState get() = _viewState as StateFlow<RegisterScreenState>

	override fun reduce(event: RegisterScreenEvent) {
		when (event) {
			is RegisterScreenEvent.OnEmailInput -> _viewState.update {
				it.copy(
					email = event.input,
					emailState = if (event.input.isNotEmpty()) {
						if (isEmailValid(event.input)) TextFieldState.VALID
						else TextFieldState.INVALID
					} else TextFieldState.UNTOUCHED
				)
			}
			is RegisterScreenEvent.OnUsernameInput -> _viewState.update {
				it.copy(
					username = event.input,
					usernameState = if (event.input.isNotEmpty()) {
						if (!event.input.contains(';')) TextFieldState.VALID
						else TextFieldState.INVALID
					} else TextFieldState.UNTOUCHED
				)
			}
			is RegisterScreenEvent.OnPasswordInput -> _viewState.update {
				it.copy(
					password = event.input,
					passwordState = if (event.input.isNotEmpty()) {
						if (isPasswordValid(event.input)) TextFieldState.VALID
						else TextFieldState.INVALID
					} else TextFieldState.UNTOUCHED
				)
			}
			is RegisterScreenEvent.OnPasswordVisibilityClick -> _viewState.update {
				it.copy(
					isConfirmVisible = !it.isConfirmVisible
				)
			}
			is RegisterScreenEvent.OnConfirmInput -> _viewState.update {
				it.copy(
					confirm = event.input,
					confirmState = if (event.input.isNotEmpty()) {
						if (event.input == it.password && isPasswordValid(event.input)) TextFieldState.VALID
						else TextFieldState.INVALID
					} else TextFieldState.UNTOUCHED
				)
			}
			is RegisterScreenEvent.OnConfirmVisibilityClick -> _viewState.update {
				it.copy(
					isConfirmVisible = !it.isConfirmVisible
				)
			}
			is RegisterScreenEvent.OnRegisterClick -> viewModelScope.launch {
				with(viewState.value) {
					isValid().let {
						val success = userRepository.register(
							login = email,
							password = password,
							username = username
						)
						_viewState.update {
							if (success) {
								RegisterScreenState.blank().copy(
									showSuccessMessage = true
								)
							} else {
								it.copy(
									isError = true
								)
							}
						}
					}
				}
			}
			is RegisterScreenEvent.OnAbortClick -> _viewState.update { RegisterScreenState.blank() }
		}
	}
}