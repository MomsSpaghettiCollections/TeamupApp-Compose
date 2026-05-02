plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinSerialization)
}

android {
    namespace = "com.ups.android.apps.feature.main.presentation"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.designsystem)

    implementation(projects.feature.main.domain)

    implementation(projects.feature.onboarding.presentation)
    implementation(projects.feature.transaction.presentation)
    implementation(projects.feature.dashboard.presentation)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(platform(libs.koin.bom))

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.splashscreen)

}