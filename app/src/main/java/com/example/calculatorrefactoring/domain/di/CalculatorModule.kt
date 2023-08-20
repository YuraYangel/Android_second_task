package com.example.calculatorrefactoring.domain.di

import com.example.calculatorrefactoring.data.CalculatorRepositoryImpl
import com.example.calculatorrefactoring.domain.repository.CalculatorRepository
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
    fun provideCalculatorRepositoryImpl() = CalculatorRepositoryImpl()

}