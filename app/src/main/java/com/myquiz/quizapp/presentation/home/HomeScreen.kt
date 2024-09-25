package com.myquiz.quizapp.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myquiz.quizapp.presentation.common.AppDropDownMenu
import com.myquiz.quizapp.presentation.common.ButtonBox
import com.myquiz.quizapp.presentation.home.component.HomeHeader
import com.myquiz.quizapp.presentation.utils.Constants
import com.myquiz.quizapp.presentation.utils.Dimens.MediumPadding
import com.myquiz.quizapp.presentation.utils.Dimens.MediumSpacerHeight
import com.myquiz.quizapp.presentation.utils.Dimens.SmallSpacerHeight

@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (HomeScreenEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Number of Questions : ", menuList = Constants.numbersAsString,
            onDropDownClick = { event(HomeScreenEvent.SetNumberOfQuizzes(it.toInt())) }, // Calls the event function to update the state
            text = state.numberOfQuizzes.toString(),
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Category : ", menuList = Constants.categories,
            onDropDownClick = { event(HomeScreenEvent.SetQuizCategory(it)) }, // Calls the event function to update the state
            text = state.category
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty : ", menuList = Constants.difficulty,
            onDropDownClick = { event(HomeScreenEvent.SetQuizDifficulty(it)) }, // Calls the event function to update the state
            text = state.difficulty
        )

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type : ", menuList = Constants.type,
            onDropDownClick = { event(HomeScreenEvent.SetQuizType(it)) }, // Calls the event function to update the state
            text = state.type
        )

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(
            text = "Generate Quiz", padding = MediumPadding,
            onButtonClick = {
                Log.d("quiz detail", "${state.numberOfQuizzes} ${state.category} ${state.type}")
            }
        )
    }
}
