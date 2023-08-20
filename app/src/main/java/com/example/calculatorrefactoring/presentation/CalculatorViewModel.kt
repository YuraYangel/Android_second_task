package com.example.calculatorrefactoring.presentation

import androidx.lifecycle.ViewModel
import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepositoryImpl,
) : ViewModel() {

    private val _resultState = MutableStateFlow(CalculatorState())
    val resultState: StateFlow<CalculatorState> = _resultState

    private fun performCalculation() {
        calculatorRepository.calculate(_resultState)
    }

    private fun inputNumber(number: SymbolEnum) {
        calculatorRepository.enterNumber(number, _resultState)
    }

    private fun inputOperator(operation: SymbolEnum) {
        calculatorRepository.enterOperation(operation, _resultState)
    }

    private fun inputDecimal() {
        calculatorRepository.enterDecimal(_resultState)
    }

    private fun clearExpression() {
        _resultState.value = CalculatorState()
    }

    fun performAction(action: SymbolEnum) {
        when (action) {
            SymbolEnum.EQUAL -> performCalculation()
            SymbolEnum.PLUS -> inputOperator(action)
            SymbolEnum.MINUS -> inputOperator(action)
            SymbolEnum.MULTIPLY -> inputOperator(action)
            SymbolEnum.DIVIDE -> inputOperator(action)
            SymbolEnum.CLEAR -> clearExpression()
            SymbolEnum.DOT -> inputDecimal()
            else -> inputNumber(action)
        }
    }

    fun deleteLastCharacter() {
        calculatorRepository.deleteLastCharacter(_resultState)
    }


}