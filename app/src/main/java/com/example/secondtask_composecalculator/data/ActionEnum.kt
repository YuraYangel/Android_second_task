package com.example.secondtask_composecalculator.data

enum class ActionEnum(val symbol: String) {
    PLUS("+"),
    MINUS("-"),
    CLEAR("AC"),
    SIGN("±"),
    PERCENT("%"),
    DIVIDE("÷"),
    MULTIPLY("×"),
    CALCULATE("="),
    DOUBLE(","),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

}

fun getOperatorBySymbol(symbol: Char): ActionEnum? {
    return when (symbol) {
        '+' -> ActionEnum.PLUS
        '-' -> ActionEnum.MINUS
        '×' -> ActionEnum.MULTIPLY
        '÷' -> ActionEnum.DIVIDE
        else -> null
    }
}
