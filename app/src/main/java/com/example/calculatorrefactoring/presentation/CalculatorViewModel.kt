package com.example.calculatorrefactoring.presentation

import androidx.lifecycle.ViewModel
import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.data.utils.SymbolEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepositoryImpl,
) : ViewModel() {

    private val _resultState = MutableStateFlow<CalculatorState>(CalculatorState())
    val resultState: StateFlow<CalculatorState> = _resultState


    fun onAction(action: SymbolEnum){
        when (action){
            SymbolEnum.EQUAL -> calculatorRepository.calculate(_resultState)
            SymbolEnum.PLUS -> calculatorRepository.enterOperation(action, _resultState)
            SymbolEnum.MINUS -> calculatorRepository.enterOperation(action, _resultState)
            SymbolEnum.MULTIPLY -> calculatorRepository.enterOperation(action, _resultState)
            SymbolEnum.DIVIDE -> calculatorRepository.enterOperation(action, _resultState)
            else -> calculatorRepository.enterNumber(action, _resultState)
        }



    }




}