package com.example.shared.di

import com.example.shared.data.remote.RemoteDataSource
import com.example.shared.data.remote.createHttpClient
import io.ktor.client.HttpClient
import org.koin.core.annotation.Single

@Single
fun provideHttpClient() = createHttpClient()

@Single
fun providesRemoteDataSource(httpClient: HttpClient): RemoteDataSource {
    return RemoteDataSource(httpClient)
}

