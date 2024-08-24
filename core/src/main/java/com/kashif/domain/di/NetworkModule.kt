package com.kashif.domain.di


import com.example.shared.data.remote.RemoteDataSource
import com.example.shared.data.remote.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient()= createHttpClient()

    @Provides
    @Singleton
    fun providesRemoteDataSource(httpClient: HttpClient): RemoteDataSource {
        return RemoteDataSource(httpClient)
    }

}
