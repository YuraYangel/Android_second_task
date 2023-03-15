package com.example.secondtask_composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.secondtask_composecalculator.data.ActionEnum
import com.example.secondtask_composecalculator.data.CalculatorAction
import com.example.secondtask_composecalculator.ui.theme.*


val buttonMatrix: List<List<ActionEnum>> = listOf(
    listOf(ActionEnum.CLEAR, ActionEnum.SIGN, ActionEnum.PERCENT, ActionEnum.DIVIDE),
    listOf(ActionEnum.SEVEN, ActionEnum.EIGHT, ActionEnum.NINE, ActionEnum.MULTIPLY),
    listOf(ActionEnum.FOUR, ActionEnum.FIVE, ActionEnum.SIX, ActionEnum.MINUS),
    listOf(ActionEnum.ONE, ActionEnum.TWO, ActionEnum.THREE, ActionEnum.PLUS),
    listOf(ActionEnum.ZERO, ActionEnum.DOUBLE, ActionEnum.CALCULATE)
)
val action = CalculatorAction()

@Preview(showBackground = true)
@Composable
fun CalculatorView() {
    DynamicTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            TextView()
            KeyboardView()
        }
    }

}


@Composable
fun TextView() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    )
    {
        Text(
            text = MainLabel,
            fontSize = LabelSize,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = GoogleSansMedium,
            modifier = Modifier
                .padding(
                    start = MainExpressionPadding,
                    top = MainExpressionPadding
                )
                .fillMaxWidth()
        )
        Text(
            text = action.expression.value,
            fontSize = MainExpressionSize,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    start = MainExpressionPadding,
                    top = MainExpressionPadding
                )
                .fillMaxWidth(),
            fontFamily = GoogleSansMedium,
            maxLines = 2,
        )
    }
}


@Composable
fun KeyboardView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = ColumnPadding,
                top = ColumnPadding,
            ),
        verticalArrangement = Arrangement.Bottom
    )
    {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(
                    end = DeleteEndPadding,
                    bottom = DeleteBottomPadding,
                    top = DeleteTopPadding
                ),
            horizontalArrangement = Arrangement.End
        )
        {
            DeleteButton(
                { action.oneCharDelete() },
                changeDelColor()
            )
        }
        Divider(
            modifier = Modifier.padding(bottom = DividerPadding, end = DividerPadding),
            thickness = DividerThickness,
            color = Color.DarkGray
        )
        buttonMatrix.forEach { buttons ->
            KeyboardRow(buttons = buttons)
        }
    }
}


@Composable
fun KeyboardRow(buttons: List<ActionEnum>) {
    var modifier: Modifier
    var fontSize: TextUnit
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceAround
    )
    {
        buttons.forEach { buttonSymbol ->
            modifier = if (buttonSymbol == ActionEnum.ZERO) {
                Modifier
                    .weight(2.2f)
                    .aspectRatio(2.2f)
            } else {
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            }
            fontSize = if (buttonSymbol.symbol == ActionEnum.CLEAR.symbol) {
                ClearButtonSize
            } else {
                DefaultButtonSize
            }
            Box(
                modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
            )
            {
                ButtonModel(
                    buttonSymbol = buttonSymbol,
                    onClick = { action.handleButtonClick(buttonSymbol) },
                    color = getButtonColor(buttonSymbol),
                    fontSize = fontSize,
                    fontColor = getFontColor(buttonSymbol = buttonSymbol)
                )
            }
            Spacer(
                modifier = Modifier
                    .width(SpacerPadding)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(SpacerPadding)
            .background(MaterialTheme.colorScheme.background)
    )
}


@Composable
fun getButtonColor(buttonSymbol: ActionEnum): Color {
    when (buttonSymbol) {
        ActionEnum.PLUS, ActionEnum.DIVIDE, ActionEnum.MINUS, ActionEnum.CALCULATE, ActionEnum.MULTIPLY -> {
            return MaterialTheme.colorScheme.primary
        }
        else -> {
            return MaterialTheme.colorScheme.primaryContainer
        }
    }
}

// сделал эти методы Composable, потому что копилятор не даёт возвращать цвета из Material3, если функция не Composable
@Composable
fun getFontColor(buttonSymbol: ActionEnum): Color {
    when (buttonSymbol) {
        ActionEnum.PLUS, ActionEnum.DIVIDE, ActionEnum.MINUS, ActionEnum.CALCULATE, ActionEnum.MULTIPLY -> {
            return MaterialTheme.colorScheme.onPrimary
        }
        else -> {
            return MaterialTheme.colorScheme.onPrimaryContainer
        }
    }
}

fun changeDelColor(): Color {
    val color: Color
    color = when (action.expression.value) {
        "" -> Color.DarkGray
        else -> Color.White

    }
    return color
}
