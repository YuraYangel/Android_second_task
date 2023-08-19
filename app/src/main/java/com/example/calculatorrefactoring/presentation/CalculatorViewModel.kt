package com.example.calculatorrefactoring.presentation

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.calculatorrefactoring.data.CalculatorRepository
import com.example.calculatorrefactoring.data.SymbolEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : ViewModel() {

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private val _expressionState = MutableStateFlow(true)
    val expressionState: StateFlow<Boolean> = _expressionState

    private val _operatorState = MutableStateFlow("")
    val operatorState: StateFlow<String> = _operatorState

    private val _firstNumber = MutableStateFlow("")
    val firstNumber: StateFlow<String> = _firstNumber

    private val _secondNumber = MutableStateFlow("")
    val secondNumber: StateFlow<String> = _secondNumber



    fun getSymbolAction(
      actionSymbol: SymbolEnum,
    ) {
        if (actionSymbol.symbolIsOperator(actionSymbol.symbol)) {
            _operatorState.value += actionSymbol.symbol
            _result.value += actionSymbol.symbol
        }

        if (!actionSymbol.symbolIsOperator(actionSymbol.symbol) && !actionSymbol.symbolIsAction(
                actionSymbol.symbol
            )
        ) {
            _result.value += actionSymbol.symbol
            if (expressionState.value){
                _firstNumber.value += actionSymbol.symbol
                _expressionState.value = false
            }
            else{
                _secondNumber.value += actionSymbol.symbol
            }
        } else {
            when (actionSymbol) {
                SymbolEnum.EQUAL -> {
                    calculateExpression(
                        firstNumber.value,
                        secondNumber.value,
                        operatorState.value
                    )
                    _expressionState.value = true
                }

                else -> {}
            }
        }

    }


    private fun calculateExpression(firstNumber: String, secondNumber: String, operator: String) {
        _result.value = calculatorRepository.calculate(firstNumber, secondNumber, operator)
        _firstNumber.value = calculatorRepository.calculate(firstNumber, secondNumber, operator)
        _secondNumber.value = ""
        _operatorState.value = ""
        _expressionState.value = true
    }


}