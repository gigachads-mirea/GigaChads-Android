package com.rodyapal.gigachads.di

import com.rodyapal.gigachads.model.repository.CommentRepository
import com.rodyapal.gigachads.model.repository.GameRepository
import com.rodyapal.gigachads.model.repository.PostRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.model.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
	single { UserRepository(get()) }
	single { ServerRepository(get(), get()) }
	single { CommentRepository(get(), get()) }
	single { GameRepository(get(), get()) }
	single { PostRepository(get(), get(), get(), get()) }
}