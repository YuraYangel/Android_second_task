package com.example.calculatorrefactoring.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculatorrefactoring.data.SymbolEnum
import com.example.calculatorrefactoring.ui.theme.GoogleSansBold
import com.example.calculatorrefactoring.ui.theme.MediumFontSize


@Composable
fun ButtonSection(){
    val buttonSymbols: List<List<SymbolEnum>> = listOf(
        listOf(SymbolEnum.CLEAR, SymbolEnum.NEGATIVE, SymbolEnum.PERCENT, SymbolEnum.DIVIDE),
        listOf(SymbolEnum.SEVEN, SymbolEnum.EIGHT, SymbolEnum.NINE, SymbolEnum.DIVIDE)
    )

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