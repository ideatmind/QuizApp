package com.myquiz.quizapp.presentation.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.quiz.component.Dimens

@Composable
fun QuizInterface(
    onOptionSelected: (Int) -> Unit,
    qNumber: Int,
    modifier: Modifier = Modifier,
    quizState: QuizState
) {
    val question = quizState.quiz?.question!!.replace("&quot;","\"").replace("&amp;","\"").replace("&rsquo;","\"").replace("&#039;","\'")

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$qNumber.",
                    color = colorResource(R.color.blue_grey),
                    fontSize = Dimens.MediumTextSize
                )
                Text(
                    modifier = Modifier.weight(9f),
                    text = question,
                    color= colorResource(R.color.blue_grey),
                    fontSize = Dimens.MediumTextSize
                )
            }

            Spacer(Modifier.height(Dimens.LargeSpacerHeight))

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                val options = listOf(
                    "A" to quizState.shuffledOptoins[0].replace("&quot;","\"").replace("&amp;","\"").replace("&rsquo;","\"").replace("&#039;","\'"),
                    "B" to quizState.shuffledOptoins[1].replace("&quot;","\"").replace("&amp;","\"").replace("&rsquo;","\"").replace("&#039;","\'"),
                    "C" to quizState.shuffledOptoins[2].replace("&quot;","\"").replace("&amp;","\"").replace("&rsquo;","\"").replace("&#039;","\'"),
                    "D" to quizState.shuffledOptoins[3].replace("&quot;","\"").replace("&amp;","\"").replace("&rsquo;","\"").replace("&#039;","\'")
                )

                Column {
                    options.forEachIndexed { index, (optionNumber, optionText) ->
                        if (optionText.isNotBlank()) {
                          QuizOption(
                              options = optionText,
                              selected = quizState.selectedOption == index,
                              onOptionClick = { onOptionSelected(index) },
                              optionNUmber = optionNumber,
                              onUnselectOption = { onOptionSelected(-1) },
                          )
                        }
                        Spacer(Modifier.height(Dimens.MediumSpacerHeight))
                    }
                }
                Spacer(Modifier.height(Dimens.ExtraLargeSpacerHeight))
            }
        }
    }
}