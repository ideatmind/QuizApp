package com.myquiz.quizapp.data.remote.dto

import com.myquiz.quizapp.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)