package com.myquiz.quizapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myquiz.quizapp.R
import com.myquiz.quizapp.presentation.utils.Dimens
import com.myquiz.quizapp.presentation.utils.Dimens.SmallSpacerHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
  menuName: String,
  menuList: List<String>,
  text: String,
  onDropDownClick:(String) -> Unit
){

  var isExpanded  by  remember {
    mutableStateOf(false)
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = Dimens.MediumPadding)
  ) {
    Text(
      text = menuName,
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold,
      color = colorResource(R.color.blue_grey)
    )

    Spacer(modifier = Modifier.height(SmallSpacerHeight))

    ExposedDropdownMenuBox(
      expanded = isExpanded,
      onExpandedChange = {isExpanded = !isExpanded}
    ) {
      OutlinedTextField(
        modifier = Modifier
          .menuAnchor()
          .fillMaxWidth(),
        value = text,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
          ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
          unfocusedTextColor = colorResource(R.color.blue_grey),
          focusedTextColor = colorResource(R.color.blue_grey),
          unfocusedTrailingIconColor = colorResource(R.color.orange),
          focusedTrailingIconColor = colorResource(R.color.orange),
          focusedBorderColor = colorResource(R.color.dark_slate_blue),
          unfocusedBorderColor = colorResource(R.color.dark_slate_blue),
          containerColor = colorResource(R.color.dark_slate_blue)
        ),
        shape = RoundedCornerShape(15.dp)
      )

      DropdownMenu(
        modifier = Modifier
          .background(colorResource(R.color.mid_night_blue))
          .wrapContentHeight(),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }
      ) {
        menuList.forEachIndexed { index:Int, text: String ->
          DropdownMenuItem(
            text = { Text(text = text, color = colorResource(R.color.blue_grey))},
            onClick = {
              onDropDownClick(menuList[index]) // Calls the passed function to handle the click event
              isExpanded = false
            },
            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
          )
        }
      }
    }
  }
}
