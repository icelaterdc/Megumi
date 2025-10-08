// settings.gradle.kts
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") } // JitPack (KizzyRPC vb. için)
    }
    plugins {
        // plugin versiyonlarını burada sabitle (opsiyonel)
        id("com.android.application") version "8.1.1" apply false
        id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    }
}

dependencyResolutionManagement {
    // Proje içi repositories eklenmesine izin vermiyoruz — hepsini burada tanımla
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // gerekli özel repository'leri buraya ekle
    }
}

rootProject.name = "KizzyLikeApp"
include(":app")
