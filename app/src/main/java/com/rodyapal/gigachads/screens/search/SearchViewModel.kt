package com.rodyapal.gigachads.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodyapal.gigachads.model.Reducer
import com.rodyapal.gigachads.model.repository.GameRepository
import com.rodyapal.gigachads.model.repository.ServerRepository
import com.rodyapal.gigachads.screens.favservers.model.ServerBasicInfo
import com.rodyapal.gigachads.screens.search.model.SearchScreenEvent
import com.rodyapal.gigachads.screens.search.model.SearchScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
	private val serverRepository: ServerRepository,
	private val gameRepository: GameRepository
) : ViewModel(), Reducer<SearchScreenEvent> {

	private val _viewState =
		MutableStateFlow<SearchScreenState>(SearchScreenState.Display(emptyList()))
	val viewState get() = _viewState as StateFlow<SearchScreenState>

	override fun reduce(event: SearchScreenEvent) {
		when (val state = viewState.value) {
			is SearchScreenState.Display -> reduce(state, event)
			is SearchScreenState.Search -> reduce(state, event)
		}
	}

	private fun reduce(state: SearchScreenState.Display, event: SearchScreenEvent) {
		when (event) {
			is SearchScreenEvent.EnterScreen -> fetchData()
			is SearchScreenEvent.OnSearchClick -> _viewState.update {
				SearchScreenState.Search(
					query = "",
					suggestions = state.searchHistory
				)
			}

			else -> throw Exception("Invalid state ($state) for event ($event)")
		}
	}

	private fun reduce(state: SearchScreenState.Search, event: SearchScreenEvent) {
		when (event) {
			is SearchScreenEvent.OnQueryChange -> viewModelScope.launch {
				_viewState.update {
					SearchScreenState.Search(
						query = event.query,
						suggestions = serverRepository.getServerSearchSuggestions(
							event.query.filterNot { it == ';' || it == '%' || it == '_' }
						).map {
							ServerBasicInfo(
								serverId = it.server.serverId,
								serverName = it.server.name,
								gameName = it.gameName
							)
						}
					)
				}
			}

			is SearchScreenEvent.OnQueryCompleted -> viewModelScope.launch {
				_viewState.update {
					state.copy(
						suggestions = serverRepository.searchServer(state.query).map {
							ServerBasicInfo(
								serverId = it.serverId,
								serverName = it.name,
								gameName = gameRepository.getById(it.gameId).name
							)
						}
					)
				}
			}

			is SearchScreenEvent.OnAbortSearch -> fetchData()
			is SearchScreenEvent.OnClearInput -> viewModelScope.launch {
				_viewState.update {
					state.copy(
						query = "",
						suggestions = serverRepository.getServerSearchHistory().map {
							ServerBasicInfo(
								serverId = it.serverId,
								serverName = it.name,
								gameName = gameRepository.getById(it.gameId).name
							)
						})
				}
			}

			is SearchScreenEvent.OnServerSelected -> viewModelScope.launch {
				serverRepository.setWasSearched(event.serverId)
			}

			else -> throw Exception("Invalid state ($state) for event ($event)")
		}
	}

	private fun fetchData() = viewModelScope.launch {
		_viewState.update {
			SearchScreenState.Display(
				searchHistory = serverRepository.getServerSearchHistory().map {
					ServerBasicInfo(
						serverId = it.serverId,
						serverName = it.name,
						gameName = gameRepository.getById(it.gameId).name
					)
				}
			)
		}
	}
}