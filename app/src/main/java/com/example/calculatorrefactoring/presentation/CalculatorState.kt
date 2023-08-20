package com.example.calculatorrefactoring.presentation

import com.example.calculatorrefactoring.data.SymbolEnum

data class CalculatorState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val operator: SymbolEnum
)