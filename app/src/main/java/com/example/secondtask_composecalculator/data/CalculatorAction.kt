package com.example.secondtask_composecalculator.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

const val EMPTY_STRING = ""

class CalculatorAction {
    private var numberIsClicked: Boolean = false
    private var actionIsClicked: Boolean = false
    private var errorInString: Boolean = false
    private var doubleInExpression: Boolean = false
    private val operationsArray: List<Char> = listOf('-', '+', '×', '÷')
    val expression: MutableState<String> = mutableStateOf(EMPTY_STRING)

    fun handleButtonClick(buttonSymbol: ActionEnum) {
        when (buttonSymbol) {
            ActionEnum.PLUS, ActionEnum.DIVIDE,
            ActionEnum.MULTIPLY, ActionEnum.MINUS -> addActionOnExpression(buttonSymbol)
            ActionEnum.SIGN -> signChange()
            ActionEnum.CALCULATE -> calculateSignSearch()
            ActionEnum.PERCENT -> toPercent()
            ActionEnum.CLEAR -> clearExpression()
            ActionEnum.DOUBLE -> toDouble()
            else -> addNumberOnExpression(buttonSymbol)
        }
    }


    private fun addNumberOnExpression(buttonSymbol: ActionEnum) {
        expression.value += buttonSymbol.symbol
        numberIsClicked = true
    }

    fun oneCharDelete() {
        if (expression.value.isEmpty()) {
            return
        }
        expression.value = expression.value.substring(0, expression.value.length - 1)
        actionIsClicked = false
        errorInString = false
        doubleInExpression = false
    }

    private fun addActionOnExpression(buttonSymbol: ActionEnum) {
        if (actionIsClicked || errorInString) {
            return
        }
        expression.value += buttonSymbol.symbol
        actionIsClicked = true
        doubleInExpression = false
    }


    private fun clearExpression() {
        actionIsClicked = false
        numberIsClicked = false
        doubleInExpression = false
        expression.value = EMPTY_STRING
    }


    private fun toDouble() {
        if (doubleInExpression) {
            return
        }
        expression.value += "."
        doubleInExpression = true
    }


    private fun calculateSignSearch() {
        if (!actionIsClicked) {
            return
        }
        actionIsClicked = false
        numberIsClicked = false
        doubleInExpression = false
        var firstNumber = EMPTY_STRING
        var secondNumber = EMPTY_STRING
        var indexBeforeOperator = 1
        val exp = expression.value
        firstNumber += exp[0]

        while (!operationsArray.contains(exp[indexBeforeOperator])) {
            firstNumber += exp[indexBeforeOperator]
            indexBeforeOperator += 1
            if (indexBeforeOperator > exp.length - 1) {
                expression.value = firstNumber
            }
        }

        var indexAfterOperator = indexBeforeOperator + 1
        while (indexAfterOperator <= exp.length - 1) {
            secondNumber += exp[indexAfterOperator]
            indexAfterOperator += 1
        }
        calculateExpression(exp, firstNumber, secondNumber, indexBeforeOperator)
    }


    private fun calculateExpression(
        exp: String, firstNumber: String, secondNumber: String, indexBeforeOperator: Int
    ) {

        val result: String
        val operator = getOperatorBySymbol(exp[indexBeforeOperator])
        when (operator) {
            ActionEnum.PLUS -> result =
                (firstNumber.toDouble() + secondNumber.toDouble()).toString()
            ActionEnum.MINUS -> result =
                (firstNumber.toDouble() - secondNumber.toDouble()).toString()
            ActionEnum.MULTIPLY -> result =
                (firstNumber.toDouble() * secondNumber.toDouble()).toString()
            ActionEnum.DIVIDE -> if (secondNumber.toInt() == 0) {
                result = "Error"
                errorInString = true
            } else {
                result = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
            }
            else -> {
                result = "Error"
            }
        }

        if (result.endsWith(".0")) {
            expression.value = result.substring(0, result.length - 2)
        } else {
            expression.value = result
        }
    }

    private fun toPercent() {
        if (errorInString) {
            return
        }
        calculateSignSearch()
        var result: String = expression.value
        result = (result.toDouble() * 0.01).toString()
        expression.value = result
    }


    private fun toPositive() {
        var newExpression = EMPTY_STRING
        for (i in 1 until expression.value.length) {
            newExpression += expression.value[i]
        }
        expression.value = newExpression
    }

    private fun toNegative() {
        var newExpression = "-"
        expression.value.forEach { newExpression += it }
        expression.value = newExpression
    }

    private fun signChange() {
        if (expression.value[0] == '-') {
            toPositive()
        } else {
            toNegative()
        }
    }
}

