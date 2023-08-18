package com.example.calculatorrefactoring.data

import com.example.calculatorrefactoring.data.utils.Constants
import javax.inject.Singleton

@Singleton
class CalculatorRepository {

    private fun checkNumberType(number: String) = number.contains(".")

    private fun calculateInt(firstNumber: Int, secondNumber: Int, operator: String): String {
        return when (operator) {
            "+" -> (firstNumber + secondNumber).toString()
            "-" -> (firstNumber - secondNumber).toString()
            "*" -> (firstNumber * secondNumber).toString()
            "/" -> {
                try {
                    (firstNumber / secondNumber).toString()
                } catch (e: ArithmeticException) {
                    Constants.ARITHMETIC_ERROR
                }
            }

            else -> Constants.INVALID_OPERATOR
        }
    }

    private fun calculateDouble(firstNumber: Float, secondNumber: Float, operator: String): String {
        return when (operator) {
            "+" -> (firstNumber + secondNumber).toString()
            "-" -> (firstNumber - secondNumber).toString()
            "*" -> (firstNumber * secondNumber).toString()
            "/" -> {
                try {
                    (firstNumber / secondNumber).toString()
                } catch (e: ArithmeticException) {
                    Constants.ARITHMETIC_ERROR
                }
            }

            else -> Constants.INVALID_OPERATOR
        }
    }

    fun calculate(firstNumber: String, secondNumber: String, operator: String): String {
        return if (checkNumberType(firstNumber) || checkNumberType(secondNumber)) {
            calculateDouble(firstNumber.toFloat(), secondNumber.toFloat(), operator)
        } else {
            calculateInt(firstNumber.toInt(), secondNumber.toInt(), operator)
        }
    }

}