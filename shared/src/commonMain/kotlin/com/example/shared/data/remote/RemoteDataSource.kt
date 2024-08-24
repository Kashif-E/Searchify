package com.example.shared.data.remote

import com.example.shared.data.remote.models.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RemoteDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): MovieResponse {

        return httpClient.get(Api.BASE_URL+"movie/top_rated?api_key=5a0bb0634f61c6f2491de3e7b6100ade&page=1") {

        }.body<MovieResponse>()
    }
}

expect fun getKtorEngineer(): HttpClientEngine

fun createHttpClient(): HttpClient {
    return HttpClient(getKtorEngineer()) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.INFO
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }
}

