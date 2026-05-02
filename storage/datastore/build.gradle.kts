plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.ups.android.apps.storage.datastore"
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

protobuf {
    protoc { artifact = libs.protobuf.protoc.get().toString() }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering { option("lite") }
                val kotlin by registering { option("lite") }
            }
        }
    }
}

dependencies {
    implementation(projects.common.model)

    implementation(libs.androidx.core.ktx)

    api(libs.protobuf.lite)

    api(libs.androidx.datastore)
    api(libs.androidx.datastore.core)

    api(platform(libs.koin.bom))
    api(libs.koin.android)
    api(libs.koin.core)
}
