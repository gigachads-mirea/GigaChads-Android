package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { LoginViewModel(get()) }
}