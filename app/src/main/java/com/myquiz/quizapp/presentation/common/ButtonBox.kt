package com.myquiz.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.utils.Dimens
import com.myquiz.quizapp.presentation.utils.Dimens.MediumBoxHeight
import com.myquiz.quizapp.presentation.utils.Dimens.MediumTextSize

@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = Dimens.SmallPadding,
    borderColor: Color = colorResource(R.color.blue_grey),
    containerColor: Color = colorResource(R.color.blue_grey),
    textColor: Color = colorResource(R.color.black),
    onClick: () -> Unit,
    fraction: Float = 1f,
    fontSize: TextUnit = MediumTextSize
){
    Box(
        modifier = Modifier
            .padding(padding)
            .border(1.dp,borderColor, RoundedCornerShape(Dimens.LargeCornerRadius))
            .fillMaxWidth(fraction)
            .height(MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.LargeCornerRadius))
            .background(containerColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = MediumTextSize,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor
        )
    }
}