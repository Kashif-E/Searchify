package com.example.shared.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named("ioDispatcher")
fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

@Factory
@Named("defaultDispatcher")
fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default


