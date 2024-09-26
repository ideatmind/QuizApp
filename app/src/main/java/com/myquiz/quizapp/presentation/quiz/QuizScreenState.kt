package com.myquiz.quizapp.presentation.quiz

import com.myquiz.quizapp.domain.model.Quiz

data class QuizScreenState(
    val isLoading: Boolean = false,
    val quizState: List<QuizState> = emptyList(),
    val error: String = "",
    var score : Int = 0
)

data class QuizState(
    val quiz : Quiz ? = null,
    val shuffledOptoins: List<String>  = emptyList(),
    val  selectedOption: Int ? = -1
)