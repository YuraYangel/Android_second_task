package com.example.calculatorrefactoring.data

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


    fun symbolIsAction(symbol: String): Boolean {
        if (symbol == CLEAR.symbol || symbol == PERCENT.symbol
            || symbol == NEGATIVE.symbol || symbol == DOT.symbol || symbol == EQUAL.symbol
        ) {
            return true
        }
        return false
    }

    fun symbolIsOperator(symbol: String): Boolean {
        if (symbol == PLUS.symbol || symbol == MINUS.symbol
            || symbol == MULTIPLY.symbol || symbol == DIVIDE.symbol
        ) {
            return true
        }
        return false
    }

}