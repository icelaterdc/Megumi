Kizzy-like Android skeleton (Kotlin + Jetpack Compose)
----------------------------------------------------

What's included:
- Minimal Android project using Kotlin + Jetpack Compose.
- Dependency on KizzyRPC via JitPack is added in `app/build.gradle.kts`.
- A simple UI (MainActivity.kt) that collects RPC fields and calls KizzyRPC.setActivity.
- GitHub Actions workflow that builds the debug APK and writes it to `output/` in repo root and uploads as artifact.

Important security & TOS notes:
- KizzyRPC examples show using a Discord account token. **Using account tokens may violate Discord's Terms of Service and can lead to account action.**
- This project does NOT include code to obtain or leak tokens. If you plan to use a token, you must supply it yourself and accept responsibility. Prefer using bot accounts and server-side solutions where possible.
- The provided CI builds a debug APK (assembleDebug) to avoid keystore/signing complexity. Debug APK is unsigned for release.
- KizzyRPC is an upstream project (https://github.com/dead8309/KizzyRPC). The app depends on it via JitPack; if JitPack or the artifact is unavailable, the Gradle build will fail.

How to build locally:
- Open in Android Studio or run from command line:
  ./gradlew assembleDebug
- Output APK: app/build/outputs/apk/debug/app-debug.apk

CI / GitHub Actions:
- The workflow at .github/workflows/android-build.yml will:
  - Checkout repo, setup JDK
  - Run ./gradlew assembleDebug
  - Copy APK to output/app-debug.apk
  - Upload artifact for download
  - Optionally commit output/app-debug.apk back to main if PUSH_TO_REPO=true (default false recommended)