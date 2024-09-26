package com.myquiz.quizapp.presentation.quiz

import androidx.lifecycle.ViewModel

sealed class QuizScreenEvent {
    data class GetQuizzes(
        val numOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String
    ) : QuizScreenEvent()

    data class SetOptionSelected(val quizStateIndex : Int, val selectedOption: Int) :QuizScreenEvent()
}