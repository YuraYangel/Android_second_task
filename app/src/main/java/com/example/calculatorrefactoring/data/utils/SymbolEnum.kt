package com.example.calculatorrefactoring.data.utils

enum class SymbolEnum(val symbol: String) {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    ZERO("0"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    EQUAL("="),
    DOT("."),
    CLEAR("C"),
    PERCENT("%"),
    NEGATIVE("±");


    fun symbolIsAction(symbol: String) = when (symbol) {
        CLEAR.symbol, PERCENT.symbol, NEGATIVE.symbol, DOT.symbol, EQUAL.symbol -> true
        else -> false
    }

    fun symbolIsOperator(symbol: String) = when (symbol) {
        PLUS.symbol, MINUS.symbol, MULTIPLY.symbol, DIVIDE.symbol -> true
        else -> false
    }
}