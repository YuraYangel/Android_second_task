package com.example.calculatorrefactoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.calculatorrefactoring.data.SymbolEnum
import com.example.calculatorrefactoring.presentation.CalculatorViewModel
import com.example.calculatorrefactoring.ui.theme.GoogleSansBold
import com.example.calculatorrefactoring.ui.theme.MediumFontSize

@Composable
fun ButtonSection(
    firstNumber: MutableState<String>,
    secondNumber: MutableState<String>,
    operator: MutableState<String>,
    calculatorViewModel: CalculatorViewModel
) {
    val buttonsContainer = listOf(
        listOf(SymbolEnum.CLEAR, SymbolEnum.NEGATIVE, SymbolEnum.PERCENT, SymbolEnum.DIVIDE),
        listOf(SymbolEnum.SEVEN, SymbolEnum.EIGHT, SymbolEnum.NINE, SymbolEnum.MULTIPLY),
        listOf(SymbolEnum.FOUR, SymbolEnum.FIVE, SymbolEnum.SIX, SymbolEnum.MINUS),
        listOf(SymbolEnum.ONE, SymbolEnum.TWO, SymbolEnum.THREE, SymbolEnum.PLUS)
    )

    val equalButton = listOf(SymbolEnum.ZERO, SymbolEnum.DOT, SymbolEnum.EQUAL)

    buttonsContainer.forEach { buttonRow ->
        KeyBoard(
            buttonRow = buttonRow,
            calculatorViewModel = calculatorViewModel,
            firstNumber = firstNumber,
            secondNumber = secondNumber,
            operator = operator
            
        )
    }

    EqualRow(
        buttonRow = equalButton,
        calculatorViewModel = calculatorViewModel,
        firstNumber = firstNumber,
        secondNumber = secondNumber,
        operator = operator
    )

}


@Composable
fun EqualRow(
    firstNumber: MutableState<String>,
    secondNumber: MutableState<String>,
    operator: MutableState<String>,
    buttonRow: List<SymbolEnum>,
    calculatorViewModel: CalculatorViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        buttonRow.forEach {
            val width = if (it == SymbolEnum.ZERO) 182.dp else 83.dp
            Button(
                modifier = Modifier
                    .height(83.dp)
                    .width(width),
                shape = RoundedCornerShape(size = 28.dp),
                onClick = {
                    calculatorViewModel.getSymbolAction(
                        firstNumber = firstNumber.value,
                        secondNumber = secondNumber.value,
                        actionSymbol = it,
                    )
                },
            ) {
                Text(
                    text = it.symbol,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = MediumFontSize,
                        fontFamily = GoogleSansBold
                    )
                )
            }
        }
    }
}

@Composable
fun KeyBoard(
    firstNumber: MutableState<String>,
    secondNumber: MutableState<String>,
    operator: MutableState<String>,
    buttonRow: List<SymbolEnum>,
    calculatorViewModel: CalculatorViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        buttonRow.forEach {
            Button(
                modifier = Modifier.size(83.dp),
                shape = RoundedCornerShape(size = 28.dp),
                onClick = {
                    calculatorViewModel.getSymbolAction(
                        firstNumber = firstNumber.value,
                        secondNumber = secondNumber.value,
                        actionSymbol = it,
                    )
                },
            ) {
                Text(
                    text = it.symbol,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = MediumFontSize,
                        fontFamily = GoogleSansBold
                    )
                )
            }
        }
    }
}