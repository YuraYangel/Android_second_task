package com.example.calculatorrefactoring.presentation

import android.view.View
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.calculatorrefactoring.data.CalculatorRepository
import com.example.calculatorrefactoring.data.SymbolEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : ViewModel() {

    private val _result = MutableStateFlow("")
    val result = _result

    private val _expressionState = MutableStateFlow(false)
    val expressionState = _expressionState



    fun getSymbolAction(
        firstNumber: String, secondNumber: String, actionSymbol: SymbolEnum,
    ) {
        if (actionSymbol.symbolIsOperator(actionSymbol.symbol)) {
            _result.value += actionSymbol.symbol
            _expressionState.value = false
        }

        if (!actionSymbol.symbolIsOperator(actionSymbol.symbol) && !actionSymbol.symbolIsAction(
                actionSymbol.symbol
            )
        ) {
            result.value += actionSymbol.symbol
        } else {
            when (actionSymbol) {
                SymbolEnum.EQUAL -> {
                    calculateExpression(
                        firstNumber,
                        secondNumber,
                        actionSymbol.symbol
                    )
                    _expressionState.value = true
                }

                else -> {}
            }
        }

    }


    private fun calculateExpression(firstNumber: String, secondNumber: String, operator: String) {
        _result.value = calculatorRepository.calculate(firstNumber, secondNumber, operator)
    }


}