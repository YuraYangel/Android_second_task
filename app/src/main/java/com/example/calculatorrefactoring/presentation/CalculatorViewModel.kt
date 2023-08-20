package com.example.calculatorrefactoring.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.data.utils.Constants
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepositoryImpl,
) : ViewModel() {

    private val _resultState = MutableStateFlow(CalculatorState())
    val resultState: StateFlow<CalculatorState> = _resultState

    private fun performCalculation() {
        viewModelScope.launch {
            calculatorRepository.calculate(_resultState)
        }
    }

    private fun inputNumber(number: SymbolEnum) {
        viewModelScope.launch {
            calculatorRepository.enterNumber(number, _resultState)
        }
    }

    private fun inputOperator(operation: SymbolEnum) {
        viewModelScope.launch {
            calculatorRepository.enterOperation(operation, _resultState)
        }
    }

    private fun inputDecimal() {
        viewModelScope.launch {
            if (_resultState.value.firstNumber.contains(Constants.ARITHMETIC_ERROR)) {
                clearExpression()
            }

            calculatorRepository.enterDecimal(_resultState)
        }
    }

    private fun clearExpression() {
        _resultState.value = CalculatorState()
    }

    private fun percentageNumber() {
        if (_resultState.value.firstNumber.contains(Constants.ARITHMETIC_ERROR)) {
            clearExpression()
        }
        viewModelScope.launch {
            calculatorRepository.percentageNumber(_resultState)
        }
    }

    private fun changeSign() {
        if (_resultState.value.firstNumber.contains(Constants.ARITHMETIC_ERROR)) {
            clearExpression()
        }
        viewModelScope.launch {
            calculatorRepository.changeSing(_resultState)
        }

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
            SymbolEnum.PERCENT -> percentageNumber()
            SymbolEnum.NEGATIVE -> changeSign()
            else -> inputNumber(action)
        }
    }

    fun deleteLastCharacter() {
        viewModelScope.launch {
            calculatorRepository.deleteLastCharacter(_resultState)
        }
    }


}