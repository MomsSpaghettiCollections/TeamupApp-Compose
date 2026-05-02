plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.ups.android.apps.network.firebase_auth"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(projects.common.model)

    implementation(libs.firebase.auth)

    implementation(libs.googleid)

    api(platform(libs.koin.bom))
    api(libs.koin.android)
    api(libs.koin.core)
}
