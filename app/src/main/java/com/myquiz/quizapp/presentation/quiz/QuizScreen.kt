package com.myquiz.quizapp.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.common.QuizAppBar
import com.myquiz.quizapp.presentation.quiz.component.Dimens
import com.myquiz.quizapp.presentation.quiz.component.Dimens.LargeSpacerHeight
import com.myquiz.quizapp.presentation.quiz.component.Dimens.MediumCornerRadius
import com.myquiz.quizapp.presentation.quiz.component.Dimens.SmallSpacerHeight
import com.myquiz.quizapp.presentation.quiz.component.Dimens.VerySmallViewHeight

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {

        }

        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions: $numOfQuiz",
                    color = colorResource(R.color.blue_grey)
                )

                Text(
                    text = quizDifficulty,
                    color = colorResource(R.color.blue_grey)
                )
            }

            Spacer(Modifier.height(SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(VerySmallViewHeight)
                    .clip(RoundedCornerShape(MediumCornerRadius))
                    .background(
                        colorResource(R.color.blue_grey)
                    ),
            )

            Spacer(Modifier.height(SmallSpacerHeight))


            // quiz interface
        }
    }
}