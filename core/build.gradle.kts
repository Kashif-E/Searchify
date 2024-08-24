import org.gradle.internal.impldep.org.junit.platform.launcher.TagFilter.includeTags

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.androidRoom)
    alias(libs.plugins.androidJunit5)
}

android {
    namespace = "com.kashif.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "com.kashif.Runner"
        consumerProguardFiles("consumer-rules.pro")
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    hilt {
        enableAggregatingTask = false
    }

    room { schemaDirectory("$projectDir/schemas") }
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests.all {
            it.testLogging {
                events("skipped", "passed", "failed")
            }
        }
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }

}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    annotationProcessor(libs.androidx.room.compiler)
    api(libs.hilt.android)
    implementation(libs.javapoet)
    api(libs.androidx.core.ktx)
    api(libs.androidx.room.ktx)
    api(libs.androidx.room.runtime)
    api(libs.javapoet)
    api(libs.kotlinx.serialization.json)
    implementation(libs.logging.interceptor)
    api(libs.coil.compose)
    api(libs.retrofit)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.retrofit2.kotlinx.serialization.converter)
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
    kspTest(libs.hilt.android.compiler)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.javapoet)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    androidTestImplementation(libs.junit.jupiter.api)
    androidTestImplementation(libs.android.test.extensions)

    api(projects.shared)


}