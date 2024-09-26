package com.myquiz.quizapp.presentation.quiz

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.common.ButtonBox
import com.myquiz.quizapp.presentation.common.QuizAppBar
import com.myquiz.quizapp.presentation.navgraph.Routes
import com.myquiz.quizapp.presentation.quiz.component.Dimens
import com.myquiz.quizapp.presentation.quiz.component.Dimens.LargeSpacerHeight
import com.myquiz.quizapp.presentation.quiz.component.Dimens.MediumCornerRadius
import com.myquiz.quizapp.presentation.quiz.component.Dimens.MediumPadding
import com.myquiz.quizapp.presentation.quiz.component.Dimens.MediumSpacerHeight
import com.myquiz.quizapp.presentation.quiz.component.Dimens.SmallSpacerHeight
import com.myquiz.quizapp.presentation.quiz.component.Dimens.VerySmallViewHeight
import com.myquiz.quizapp.presentation.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (QuizScreenEvent) -> Unit,
    state: QuizScreenState,
    navController: NavController
) {
    BackHandler {
        navController.navigate(Routes.HomeScreen.route)
    }

    LaunchedEffect(key1 = Unit) {

        val difficulty = when(quizDifficulty) {
             "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }

        val type = when(quizType) {
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }
        event(QuizScreenEvent.GetQuizzes(numOfQuiz,Constants.categoriesMap[quizCategory]!!,difficulty,type))
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {
            navController.navigate(Routes.HomeScreen.route) {
                popUpTo(Routes.HomeScreen.route) {inclusive = true}
            }
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

            Spacer(Modifier.height(MediumSpacerHeight))


            // quiz interface
            if(quizFetched(state)){
               val pagerState = rememberPagerState() {
                   state.quizState.size
               }
                HorizontalPager(state  = pagerState) {  index ->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        quizState = state.quizState[index],
                        onOptionSelected = { selectedIndex ->
                            event(QuizScreenEvent.SetOptionSelected(index,selectedIndex))
                        },
                        qNumber = index + 1
                    )
                }
                        val buttonText by remember {
                            derivedStateOf {
                                when (pagerState.currentPage) {
                                    0 -> listOf("", "Next")
                                    state.quizState.size - 1 -> listOf("Previous", "Submit")
                                    else -> listOf("Previous", "Next")
                                }
                            }
                        }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()
                    if (buttonText[0].isNotEmpty()) {
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.SmallTextSize,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage-1) }
                            }
                        )
                    }


                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(R.color.orange),
                        containerColor = if(pagerState.currentPage == state.quizState.size-1)colorResource(R.color.orange) else colorResource(R.color.dark_slate_blue),
                        fraction = 1f,
                        textColor = colorResource(R.color.white),
                        fontSize = Dimens.SmallTextSize,
                        onClick = {
                            if(pagerState.currentPage == state.quizState.size-1) {
                               navController.navigate(Routes.ScoreScreen.passNumOfQueAndCorrectAns(state.quizState.size, state.score))
                                
                            }else{
                                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage+1) }
                            }
                        }
                    )
                }
            }


        }
    }
}

@Composable
fun quizFetched(state: QuizScreenState): Boolean {
    return when {
        state.isLoading  -> {
            ShimmerQuizInterface()
            false
        }
        state.quizState?.isNotEmpty() ==  true -> {
            true
        }

        else -> {
            Text(text = state.error.toString(),  color = colorResource(R.color.white))
                false
        }
    }

}
