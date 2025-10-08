// app/build.gradle.kts
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.kizzylike"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kizzylike"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

repositories {
    // DİKKAT: Eğer settings.gradle.kts'te repositoriesMode = FAIL_ON_PROJECT_REPOS
    // olarak ayarlandıysa burayı **boş bırak** veya tamamen kaldır.
    // Eğer hata alırsan burayı SIL ve gerekli reposu settings.gradle.kts'e taşı.
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")

    // KizzyRPC via JitPack (örnek)
    implementation("com.github.dead8309:KizzyRPC:1.0.71")
}
