package com.myquiz.quizapp.data.repository

import com.myquiz.quizapp.data.remote.QuizApi
import com.myquiz.quizapp.domain.model.Quiz
import com.myquiz.quizapp.domain.repository.QuizRepository

class QuizRepositoryImpl(private val quizApi: QuizApi): QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount,category,difficulty,type).results
    }
}