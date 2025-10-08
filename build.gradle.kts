// build.gradle.kts (root)
plugins {
    // Root-level plugin'lere gerek yok; pluginManagement settings ile yönetiliyor
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
