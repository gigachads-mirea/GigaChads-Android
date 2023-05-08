package com.rodyapal.gigachads.model.repository

import com.rodyapal.gigachads.model.dao.UserDao
import com.rodyapal.gigachads.model.entity.User
import kotlinx.coroutines.delay

class UserRepository(
	private val dao: UserDao
) {

	suspend fun getCurrent(): User? {
		//TODO network when Danya will finally make a working server
		delay(1000)
		return dao.getLoggedUser()
	}

	suspend fun auth(login: String, password: String): Boolean {
		//TODO network when Danya will finally make a working server
		delay(3000)
//		dao.save(
//			User(
//				username = "Blank user",
//				email = login,
//				password = password,
//				isClient = false,
//			)
//		)
		return true
	}

	suspend fun register(login: String, password: String, username: String): Boolean {
		//TODO network when Danya will finally make a working server
		delay(1000)
		dao.save(
			User(
				username = username,
				email = login,
				password = password,
				isClient = false,
			)
		)
		return true
	}
}