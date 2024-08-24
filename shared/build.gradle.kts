plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidLibrary)
}

kotlin {

    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {

        commonMain.dependencies {
            api(libs.ktor.json)
            api(libs.ktor.core)
            api(libs.ktor.serialization.json)
            api(libs.ktor.serialization)
            api(libs.ktor.contentnegotiation)
            api(libs.ktor.client.logging)
        }
        androidMain.dependencies {
            implementation(libs.ktor.android)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.ios)
        }
    }
}

android{

    namespace = "com.example.shared"
    compileSdk = 33
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}