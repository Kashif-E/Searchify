package com.example.shared.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getKtorEngineer(): HttpClientEngine = Darwin.create()