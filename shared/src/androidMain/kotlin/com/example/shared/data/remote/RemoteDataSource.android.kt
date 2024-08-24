package com.example.shared.data.remote

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun getKtorEngineer(): HttpClientEngine = Android.create()