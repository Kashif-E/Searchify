package com.example.shared.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Single
class A{
    fun print(){
        println("A")
    }
}

@Module
@ComponentScan
class KoinModule {

}
