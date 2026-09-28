# Secret Notes

A complete offline Android app written in Java with XML layouts. It provides a secure four-digit PIN gate, searchable notes, local SQLite storage, editing/deletion, theme settings, and an About screen.

## Open and build
1. Clone this repository and open it in Android Studio (open the folder containing `settings.gradle`).
2. Allow Gradle to sync and install Android SDK platform 35 and Build Tools when prompted.
3. Select an emulator or a USB-connected phone running Android 7.0/API 24 or newer.
4. Press **Run**, or use **Build > Build APK(s)**. The debug APK is produced at `app/build/outputs/apk/debug/app-debug.apk`.

For AIDE, import the project as a Gradle Android project, let it download the listed AndroidX/Material dependencies, then press Run. No permissions or network access are required.

On first launch choose a four-digit PIN. Notes never leave the device; the PIN is encrypted before it is stored in private app preferences.
