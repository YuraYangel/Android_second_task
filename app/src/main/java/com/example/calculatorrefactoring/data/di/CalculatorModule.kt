package com.example.calculatorrefactoring.data.di

import com.example.calculatorrefactoring.domain.CalculatorRepository
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