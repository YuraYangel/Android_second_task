package com.example.calculatorrefactoring.domain.repository

import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import com.example.calculatorrefactoring.presentation.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow

interface CalculatorRepository{

    suspend fun changeSing(resultState: MutableStateFlow<CalculatorState>)

    suspend fun percentageNumber(resultState: MutableStateFlow<CalculatorState>)

    suspend fun deleteLastCharacter(resultState: MutableStateFlow<CalculatorState>)

    suspend fun enterNumber(number: SymbolEnum, resultState: MutableStateFlow<CalculatorState>)

    suspend fun enterDecimal(resultState: MutableStateFlow<CalculatorState>)

    suspend fun enterOperation(operation: SymbolEnum, resultState: MutableStateFlow<CalculatorState>)

    suspend fun calculate(resultState: MutableStateFlow<CalculatorState>)
}