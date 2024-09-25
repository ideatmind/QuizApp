package com.myquiz.quizapp.presentation.home

data class StateHomeScreen(
    val numberOfQuizzes : Int = 10,
    val category: String = "General Knowledge",
    val difficulty: String = "Easy",
    val type: String = "Multiple Choice"
)