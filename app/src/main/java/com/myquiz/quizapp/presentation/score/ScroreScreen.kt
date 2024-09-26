package com.myquiz.quizapp.presentation.score

import android.icu.text.DecimalFormat
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.navgraph.Routes
import com.myquiz.quizapp.presentation.quiz.component.Dimens

@Composable
fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
    navController: NavController
) {
    BackHandler {
        goToHome(navController)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                onClick = {
                    goToHome(navController = navController)
                }
            ) {
                Icon(
                   Icons.Default.Clear,
                    contentDescription = "",
                    tint = colorResource(R.color.blue_grey)
                )
            }
        }
        Spacer(Modifier.height(Dimens.SmallSpacerHeight))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(colorResource(R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.MediumPadding,
                    vertical = Dimens.MediumPadding
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrg))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(Color.Black)) {
                        append("You attempted ")
                    }
                    withStyle(SpanStyle(Color.Blue)) {
                        append("$numOfQuestions questions ")
                    }
                    withStyle(SpanStyle(Color.Black)) {
                        append(" and from that ")
                    }
                    withStyle(SpanStyle(colorResource(R.color.green))) {
                        append(" $numOfCorrectAns answers ")
                    }
                    withStyle(SpanStyle(Color.Black)) {
                        append(" are correct.")
                    }
                }
                val scorePercentage = calculatePercentage(numOfCorrectAns, numOfQuestions)
                LottieAnimation(
                    modifier = Modifier.size(Dimens.LargeLottieAnimSize),
                        composition = composition,
                        iterations = 1000
                        )
                Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.MediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(R.color.green),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimens.LargeTextSize,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = "Quiz completed successfully",
                    color = colorResource(R.color.green),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimens.SmallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(Dimens.MediumSpacerHeight))

                Text(
                    text = annotatedString,
                    color = colorResource(R.color.green),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = Dimens.SmallTextSize,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
fun goToHome(navController: NavController) {
    navController.navigate(Routes.HomeScreen.route) {
        popUpTo(Routes.HomeScreen.route){inclusive = true}
    }
}


fun calculatePercentage(k: Int, n: Int): Double {
    require(k>= 0 && n>0) {"Invalid input"}
    val percentage = (k.toDouble()/n.toDouble()) * 100
    return DecimalFormat("#.##").format(percentage).toDouble()
}
