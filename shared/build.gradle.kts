import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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
            api(libs.koin.core)
            api(libs.koin.annotations)
            api(libs.koin.compose.mp)
            api(libs.koin.compose.viewmodel)
            api(libs.kotlinx.datetime)
            api(libs.lifecycle.viewmodel.compose)
            api(libs.lifecycle.runtime.compose)


        }
        androidMain.dependencies {
            api(libs.ktor.android)
        }

        nativeMain.dependencies {
            api(libs.ktor.ios)
        }
    }
}

android{

    namespace = "com.example.shared"
    compileSdk = 34
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
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)

}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
    arg("KOIN_DEFAULT_MODULE","false")
}

// KSP Metadata Trigger
project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
