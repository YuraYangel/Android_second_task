package com.example.calculatorrefactoring.domain.entity

sealed class CalculatorAction {
    data class Number(val number: String) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object Equal : CalculatorAction()
    data object Delete : CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Precentage : CalculatorAction()
    data object Negative : CalculatorAction()

}
