// build.gradle.kts (repo kökünde)
plugins {
    // Root-level build dosyası genelde plugin tanımları içermez,
    // plugin versiyonlarını settings.gradle.kts üzerinden yönetiyoruz.
    // Burada boş bırakılması güvenlidir.
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
