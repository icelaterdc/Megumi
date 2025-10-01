// settings.gradle.kts (repo köküne koyup mevcut dosyanın yerine yaz)
pluginManagement {
    repositories {
        // pluginleri buradan çözümle (Android plugin Google Maven'da olduğu için google() burada önemli)
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    plugins {
        // (opsiyonel) burada plugin versiyonlarını da sabitleyebilirsin
        id("com.android.application") version "8.1.1" apply false
        id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KizzyLikeApp"
include(":app")
