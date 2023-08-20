package com.example.calculatorrefactoring.data

import com.example.calculatorrefactoring.data.utils.Constants
import com.example.calculatorrefactoring.data.utils.Constants.EMPTY_STRING
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import com.example.calculatorrefactoring.domain.repository.CalculatorRepository
import com.example.calculatorrefactoring.presentation.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Singleton
class CalculatorRepositoryImpl : CalculatorRepository {

    override suspend fun changeSing(resultState: MutableStateFlow<CalculatorState>) {
        when {
            resultState.value.secondNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    secondNumber = (resultState.value.secondNumber.toFloat() * -1).toString()
                )

            resultState.value.firstNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    firstNumber = (resultState.value.firstNumber.toFloat() * -1).toString()
                )
        }
    }


    override suspend fun percentageNumber(
        resultState: MutableStateFlow<CalculatorState>
    ) {
        when {
            resultState.value.secondNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    secondNumber = resultState.value.secondNumber.toFloat().div(100).toString()
                )

            resultState.value.firstNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    firstNumber = resultState.value.firstNumber.toFloat().div(100).toString()
                )
        }
    }


    override suspend fun calculate(resultState: MutableStateFlow<CalculatorState>) {
        val firstNumber = resultState.value.firstNumber.toFloatOrNull()
        val secondNumber = resultState.value.secondNumber.toFloatOrNull()
        if (firstNumber != null && secondNumber != null) {
            val result = when (resultState.value.operator) {
                SymbolEnum.PLUS -> firstNumber + secondNumber
                SymbolEnum.MINUS -> firstNumber - secondNumber
                SymbolEnum.MULTIPLY -> firstNumber * secondNumber
                SymbolEnum.DIVIDE -> firstNumber / secondNumber
                null -> return
                else -> return
            }
            resultState.value = resultState.value.copy(
                firstNumber = result.toString().take(18),
                secondNumber = EMPTY_STRING,
                operator = null
            )
        }
    }

    override suspend fun enterDecimal(
        resultState: MutableStateFlow<CalculatorState>
    ) {
        if (resultState.value.operator == null && !resultState.value.firstNumber.contains(SymbolEnum.DOT.symbol)
            && resultState.value.firstNumber.isNotBlank()
        ) {
            resultState.value = resultState.value.copy(
                firstNumber = resultState.value.firstNumber + SymbolEnum.DOT.symbol
            )
            return
        } else if (!resultState.value.secondNumber.contains(SymbolEnum.DOT.symbol)
            && resultState.value.secondNumber.isNotBlank()
        ) {
            resultState.value = resultState.value.copy(
                secondNumber = resultState.value.secondNumber + SymbolEnum.DOT.symbol
            )
        }
    }

    override suspend fun deleteLastCharacter(resultState: MutableStateFlow<CalculatorState>) {
        when {
            resultState.value.secondNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    secondNumber = resultState.value.secondNumber.dropLast(1)
                )
            resultState.value.firstNumber.isNotBlank() -> resultState.value =
                resultState.value.copy(
                    firstNumber = resultState.value.firstNumber.dropLast(1)
                )
        }
    }


    override suspend fun enterNumber(
        number: SymbolEnum,
        resultState: MutableStateFlow<CalculatorState>
    ) {
        if (resultState.value.operator == null) {
            if (resultState.value.firstNumber.length >= Constants.MAX_LINE) {
                return
            }
            if (resultState.value.firstNumber.contains(Constants.ARITHMETIC_ERROR)) {
                resultState.value = resultState.value.copy(
                    firstNumber = number.symbol,
                    secondNumber = EMPTY_STRING,
                    operator = null
                )
                return
            }
            resultState.value = resultState.value.copy(
                firstNumber = resultState.value.firstNumber + number.symbol
            )
            return
        }
        if (resultState.value.secondNumber.length >= Constants.MAX_LINE) {
            return
        }
        resultState.value = resultState.value.copy(
            secondNumber = resultState.value.secondNumber + number.symbol
        )
    }

    override suspend fun enterOperation(
        operation: SymbolEnum,
        resultState: MutableStateFlow<CalculatorState>
    ) {
        if (resultState.value.firstNumber.isNotBlank()) {
            resultState.value = resultState.value.copy(operator = operation)
        }
    }
}
