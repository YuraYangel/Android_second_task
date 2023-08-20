package com.example.calculatorrefactoring.domain.entity

import com.example.calculatorrefactoring.data.utils.SymbolEnum

data class CalculatorState (
    val firstNumber: String = "",
    val secondNumber: String = "",
    val operator: SymbolEnum? = null,
)