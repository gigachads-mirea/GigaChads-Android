package com.rodyapal.gigachads.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodyapal.gigachads.R
import com.rodyapal.gigachads.screens.login.model.LoginScreenEvent
import com.rodyapal.gigachads.screens.login.model.LoginScreenState
import com.rodyapal.gigachads.utils.TextFieldState
import com.rodyapal.gigachads.utils.isEmailValid
import com.rodyapal.gigachads.utils.isPasswordValid
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
	onSuccessfulLogin: () -> Unit,
	onMoveToRegister: () -> Unit,
	onLoginError: @Composable () -> Unit,
	viewModel: LoginViewModel = koinViewModel(),
) {
	val state = viewModel.viewState.collectAsState()
	if (state.value.isLoginError) {
		onLoginError()
	}
	LoginScreenDisplay(
		state = state.value,
		onEmailInput = {
			viewModel.reduce(
				LoginScreenEvent.OnEmailInput(it)
			)
		},
		onPasswordInput = {
			viewModel.reduce(
				LoginScreenEvent.OnPasswordInput(it)
			)
		},
		onLoginClick = {
			viewModel.reduce(LoginScreenEvent.OnLoginClick {
				onSuccessfulLogin()
			})
		},
		onRegisterClick = {
			onMoveToRegister()
		},
		onPasswordVisibilityClick = {
			viewModel.reduce(LoginScreenEvent.OnPasswordVisibilityClick)
		}
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenDisplay(
	modifier: Modifier = Modifier,
	state: LoginScreenState,
	onEmailInput: (String) -> Unit,
	onPasswordInput: (String) -> Unit,
	onLoginClick: () -> Unit,
	onRegisterClick: () -> Unit,
	onPasswordVisibilityClick: () -> Unit,
) {
	if (state.isLoginInProgress) {
		LinearProgressIndicator(
			modifier = Modifier.fillMaxWidth().testTag("progressIndicator")
		)
	}
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		val focusManager = LocalFocusManager.current
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = stringResource(R.string.text_sign_in),
			fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
			fontSize = MaterialTheme.typography.titleLarge.fontSize
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.email,
			onValueChange = onEmailInput,
			label = { Text(text = stringResource(R.string.text_email)) },
			isError = !state.emailState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onNext = { focusManager.moveFocus(FocusDirection.Down) }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Next,
				keyboardType = KeyboardType.Email
			)
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = state.password,
			onValueChange = onPasswordInput,
			label = { Text(text = stringResource(R.string.text_password)) },
			isError = !state.passwordState.isValidOrUntouched(),
			keyboardActions = KeyboardActions(
				onDone = { focusManager.clearFocus() }
			),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done,
				keyboardType = KeyboardType.Password
			),
			visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
			trailingIcon = {
				IconButton(
					onClick = onPasswordVisibilityClick
				) {
					Icon(
						painter =  painterResource(
							id = if (state.isPasswordVisible) R.drawable.baseline_visibility_off_24 else R.drawable.baseline_visibility_24
						),
						contentDescription = stringResource(R.string.text_show_password)
					)
				}
			}
		)

		Spacer(modifier = Modifier.height(12.dp))

		Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			Button(
				onClick = {
					focusManager.clearFocus()
					onLoginClick()
				},
				enabled = state.isValid()
			) {
				Text(text = stringResource(R.string.text_sign_in))
			}

			FilledTonalButton(
				onClick = {
					focusManager.clearFocus()
					onRegisterClick()
				}
			) {
				Text(text = stringResource(R.string.text_sign_up))
			}
		}
	}
}

@Preview
@Composable
fun LoginPreview() {
	var state by remember {
		mutableStateOf(LoginScreenState.blank())
	}
	LoginScreenDisplay(
		state = state,
		onEmailInput = { state = state.copy(
			email = it,
			emailState = if (it.isNotBlank()) {
				if (isEmailValid(it)) {
					TextFieldState.VALID
				} else {
					TextFieldState.INVALID
				}
			} else {
				TextFieldState.UNTOUCHED
			}
		) },
		onPasswordInput = { state = state.copy(
			password = it,
			passwordState = if (it.isNotBlank()) {
				if (isPasswordValid(it)) {
					TextFieldState.VALID
				} else {
					TextFieldState.INVALID
				}
			} else {
				TextFieldState.UNTOUCHED
			}
		) },
		onLoginClick = {  },
		onRegisterClick = {  },
		onPasswordVisibilityClick = { state = state.copy(isPasswordVisible = !state.isPasswordVisible) },
	)
}