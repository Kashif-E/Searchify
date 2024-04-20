// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidRoom) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.ksp) apply  false
    alias(libs.plugins.androidJunit5) apply  false

}

configurations.all {
    resolutionStrategy.eachDependency {
        when {
            requested.name == "javapoet" -> useVersion("1.13.0")
        }
    }
}