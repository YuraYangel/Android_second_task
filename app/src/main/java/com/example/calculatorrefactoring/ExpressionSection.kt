package com.example.calculatorrefactoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.calculatorrefactoring.data.utils.Constants
import com.example.calculatorrefactoring.presentation.CalculatorViewModel
import com.example.calculatorrefactoring.ui.theme.GoogleSansBold
import com.example.calculatorrefactoring.ui.theme.LargeFontSize
import com.example.calculatorrefactoring.ui.theme.MediumFontSize


@Composable
fun ExpressionSection(calculatorViewModel: CalculatorViewModel) {
    val resultState by calculatorViewModel.result.collectAsState()
    val expressionState by calculatorViewModel.expressionState.collectAsState()

    val firstNumber = if (expressionState) remember { mutableStateOf("") } else remember {
        mutableStateOf(resultState)
    }
    val secondNumber = if (!expressionState) remember { mutableStateOf("") } else remember {
        mutableStateOf(resultState)
    }

    val symbol = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Calculator",
            color = Color.White,
            fontSize = MediumFontSize,
            fontFamily = GoogleSansBold
        )
        Spacer(modifier = Modifier.padding(32.dp))
        Text(
            text = resultState,
            maxLines = Constants.MAX_LINE,
            color = Color.White,
            fontSize = LargeFontSize,
            fontFamily = GoogleSansBold
        )
        Spacer(modifier = Modifier.padding(32.dp))
        IconButton(
            onClick = { /*TODO*/ },

            modifier = Modifier
                .align(alignment = Alignment.End)
                .size(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.delete_text_button),
                contentDescription = "Clear button",
                colorFilter = if (resultState.isEmpty()) ColorFilter.tint(Color.Gray) else ColorFilter.tint(
                    Color.White
                )
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.padding(4.dp))
        ButtonSection(
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = symbol,
            calculatorViewModel = calculatorViewModel
        )
    }


}