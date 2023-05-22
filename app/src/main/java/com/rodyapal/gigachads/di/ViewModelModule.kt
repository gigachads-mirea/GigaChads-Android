package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.screens.favservers.FavoriteServersViewModel
import com.rodyapal.gigachads.screens.login.LoginViewModel
import com.rodyapal.gigachads.screens.post.PostViewModel
import com.rodyapal.gigachads.screens.register.RegisterViewModel
import com.rodyapal.gigachads.screens.search.SearchViewModel
import com.rodyapal.gigachads.screens.server.ServerViewModel
import com.rodyapal.gigachads.screens.serverposts.ServerPostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { FavoriteServersViewModel(get(), get(), get()) }
	viewModel { LoginViewModel(get()) }
	viewModel { PostViewModel(get(), get(), get(), get()) }
	viewModel { RegisterViewModel(get()) }
	viewModel { SearchViewModel(get(), get()) }
	viewModel { ServerViewModel(get(), get(), get(), get()) }
	viewModel { ServerPostsViewModel(get(), get(), get()) }
}