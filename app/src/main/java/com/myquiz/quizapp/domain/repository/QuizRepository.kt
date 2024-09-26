package com.myquiz.quizapp.domain.repository

import com.myquiz.quizapp.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String ): List<Quiz>
}