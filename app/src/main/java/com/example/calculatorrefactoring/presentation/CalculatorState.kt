package com.example.calculatorrefactoring.presentation

import com.example.calculatorrefactoring.data.Calculator
import com.example.calculatorrefactoring.data.utils.Constants.EMPTY_STRING
import com.example.calculatorrefactoring.data.utils.SymbolEnum


data class CalculatorState(
    val firstNumber: String = EMPTY_STRING,
    val secondNumber: String = EMPTY_STRING,
    val operator: SymbolEnum? = null,
)