package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.model.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
	single { UserRepository(get()) }
}