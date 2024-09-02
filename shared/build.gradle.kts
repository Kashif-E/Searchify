plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidRoom)
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
            api(libs.androidx.room.runtime)
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
room { schemaDirectory("$projectDir/schemas") }

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}