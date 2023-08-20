package com.example.calculatorrefactoring.domain.repository

import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import com.example.calculatorrefactoring.presentation.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow

interface CalculatorRepository{

    fun enterNumber(number: SymbolEnum, resultState: MutableStateFlow<CalculatorState>)

    fun enterOperation(operation: SymbolEnum, resultState: MutableStateFlow<CalculatorState>)

    fun calculate(resultState: MutableStateFlow<CalculatorState>)
}