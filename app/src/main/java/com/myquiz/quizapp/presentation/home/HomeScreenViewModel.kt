package com.myquiz.quizapp.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel: ViewModel() {

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event: HomeScreenEvent) {
        when(event) {
            is HomeScreenEvent.SetNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuizzes = event.numberOfQuizzes)
            }
            is HomeScreenEvent.SetQuizCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)
            }
            is HomeScreenEvent.SetQuizDifficulty -> {
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }
            is HomeScreenEvent.SetQuizType -> {
                _homeState.value = homeState.value.copy(type = event.type)
            }
            else -> {}
        }
    }
}
