package com.example.calculatorrefactoring.presentation

import androidx.lifecycle.ViewModel
import com.example.calculatorrefactoring.domain.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepository,
) : ViewModel() {











}