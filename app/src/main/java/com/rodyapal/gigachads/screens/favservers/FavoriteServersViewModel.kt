package com.rodyapal.gigachads.screens.favservers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.GameRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.model.repository.UserRepository
import com.rodyapal.gigachads.screens.favservers.model.FavoriteServersScreenEvent
import com.rodyapal.gigachads.screens.favservers.model.FavoriteServersScreenState
import com.rodyapal.gigachads.screens.favservers.model.ServerBasicInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteServersViewModel(
	private val serverRepository: ServerRepository,
	private val userRepository: UserRepository,
	private val gameRepository: GameRepository
) : ViewModel(), Reducer<FavoriteServersScreenEvent> {

	private val _viewState = MutableStateFlow<FavoriteServersScreenState>(FavoriteServersScreenState.Empty)
	val viewState get() = _viewState as StateFlow<FavoriteServersScreenState>

	override fun reduce(event: FavoriteServersScreenEvent) {
		viewModelScope.launch {
			userRepository.getFavoriteServerIds().collect { ids ->
				serverRepository.getServers(ids).map { servers ->
					servers.map {
						ServerBasicInfo(
							serverId = it.serverId,
							serverName = it.name,
							gameName = gameRepository.getById(it.gameId).name
						)
					}
				}.collect { servers ->
					if (servers.isEmpty()) {
						_viewState.update {
							FavoriteServersScreenState.Empty
						}
					} else {
						_viewState.update {
							FavoriteServersScreenState.Display(
								servers = servers
							)
						}
					}
				}
			}
		}
	}
}