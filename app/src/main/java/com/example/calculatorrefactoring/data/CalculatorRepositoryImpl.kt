package com.example.calculatorrefactoring.data

import com.example.calculatorrefactoring.data.utils.Constants
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import com.example.calculatorrefactoring.domain.repository.CalculatorRepository
import com.example.calculatorrefactoring.presentation.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Singleton
class CalculatorRepositoryImpl : CalculatorRepository {

    override fun calculate(resultState: MutableStateFlow<CalculatorState>) {
        val firstNumber = resultState.value.firstNumber.toDoubleOrNull()
        val secondNumber = resultState.value.secondNumber.toDoubleOrNull()
        if (firstNumber != null || secondNumber != null) {
            val result = when (resultState.value.operator) {
                SymbolEnum.PLUS -> firstNumber!! + secondNumber!!
                SymbolEnum.MINUS -> firstNumber!! - secondNumber!!
                SymbolEnum.MULTIPLY -> firstNumber!! * secondNumber!!
                SymbolEnum.DIVIDE -> firstNumber!! / secondNumber!!
                null -> return
                else -> return
            }
            resultState.value = resultState.value.copy(
                firstNumber = result.toString().take(18),
                secondNumber = "",
                operator = null
            )
        }
    }


    override fun enterNumber(
        number: SymbolEnum,
        resultState: MutableStateFlow<CalculatorState>
    ) {
        if (resultState.value.operator == null) {
            if (resultState.value.firstNumber.length >= Constants.MAX_LINE) {
                return
            }
            if (resultState.value.firstNumber.contains("Infinity")){
                resultState.value = resultState.value.copy(
                    firstNumber = number.symbol,
                    secondNumber = "",
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

    override fun enterOperation(
        operation: SymbolEnum,
        resultState: MutableStateFlow<CalculatorState>
    ) {
        if (resultState.value.firstNumber.isNotBlank()) {
            resultState.value = resultState.value.copy(operator = operation)
        }
    }
}
