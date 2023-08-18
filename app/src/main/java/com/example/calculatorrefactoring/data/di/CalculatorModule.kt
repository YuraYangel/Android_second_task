package com.example.calculatorrefactoring.data.di

import com.example.calculatorrefactoring.data.CalculatorRepository
import com.example.calculatorrefactoring.data.SymbolEnum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CalculatorModule {

    @Provides
    @Singleton
    fun provideCalculatorRepository() = CalculatorRepository()


}