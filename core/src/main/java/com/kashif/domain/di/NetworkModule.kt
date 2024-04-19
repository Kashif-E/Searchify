package com.kashif.domain.di


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kashif.data.network.Api
import com.kashif.data.network.ApiResponseCallAdapterFactory
import com.kashif.data.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json"
        return Retrofit.Builder()
            .client(okhHttpClient)
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.parse(contentType)!!))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }


}
