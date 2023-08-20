package com.example.calculatorrefactoring.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import com.example.calculatorrefactoring.presentation.CalculatorViewModel
import com.example.calculatorrefactoring.ui.theme.GoogleSansBold
import com.example.calculatorrefactoring.ui.theme.MediumFontSize


@Composable
fun ButtonSection(calculatorViewModel: CalculatorViewModel) {
    val buttonSymbols: List<List<SymbolEnum>> = listOf(
        listOf(SymbolEnum.CLEAR, SymbolEnum.NEGATIVE, SymbolEnum.PERCENT, SymbolEnum.DIVIDE),
        listOf(SymbolEnum.SEVEN, SymbolEnum.EIGHT, SymbolEnum.NINE, SymbolEnum.MULTIPLY),
        listOf(SymbolEnum.FOUR, SymbolEnum.FIVE, SymbolEnum.SIX, SymbolEnum.MINUS),
        listOf(SymbolEnum.ONE, SymbolEnum.TWO, SymbolEnum.THREE, SymbolEnum.PLUS),
    )
    val lastButtonRow = listOf(SymbolEnum.ZERO, SymbolEnum.DOT, SymbolEnum.EQUAL)


    buttonSymbols.forEach {
        MainButtonRow(
            buttonSymbol = it,
            calculatorViewModel = calculatorViewModel
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 0 until 3) {
            val buttonWeight: Float = if (lastButtonRow[i] == SymbolEnum.ZERO) 2f else 1f
            CalculatorButton(
                symbolEnum = lastButtonRow[i],
                modifier = Modifier
                    .weight(buttonWeight)
                    .aspectRatio(buttonWeight)
                    .padding(top = 8.dp),
                onClick = {
                    calculatorViewModel.performAction(lastButtonRow[i])
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun MainButtonRow(
    buttonSymbol: List<SymbolEnum>,
    calculatorViewModel: CalculatorViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        for (i in 0 until 4) {
            CalculatorButton(
                onClick = { calculatorViewModel.performAction(buttonSymbol[i]) },
                symbolEnum = buttonSymbol[i],
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(top = 8.dp),

                )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
fun CalculatorButton(
    symbolEnum: SymbolEnum,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .clickable { onClick() }
            .then(modifier),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = symbolEnum.symbol,
            color = Color.White,
            fontSize = MediumFontSize,
            fontFamily = GoogleSansBold
        )
    }
}