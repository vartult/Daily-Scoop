@file:Suppress("MemberVisibilityCanBePrivate")
object Versions {
    const val versionMajor = 1
    const val versionMinor = 0
    const val versionPatch = 0
    const val versionBuild = 0

    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
    const val versionName = "$versionMajor.$versionMinor.$versionPatch.$versionBuild"

    const val proguard_android = "proguard-android.txt"
    const val proguard_common = "proguard-common.txt"
    const val proguard_specific = "proguard-specific.txt"
    const val applicationId = "com.example.githubing"
    const val gradle = "3.5.3"
    const val junit = "4.12"
    const val androidX = "1.0.0"
    const val appCompat = "1.1.0-alpha05"
    const val constraint = "1.1.3"
    const val liveDataKtx = "2.1.0-beta01"
    const val material = "1.1.0-alpha01"
    const val kotlin = "1.3.41"
    const val archComponent = "2.0.0"
    const val archCoreTesting = "1.1.1"
    const val dagger = "2.24"
    const val rxJava = "2.2.11"
    const val rxAndroid = "2.1.1"
    const val preferenceRoom = "1.1.8"
    const val retrofit = "2.5.0"
    const val gson = "2.8.5"
    const val timber = "4.7.1"
    const val memoryLeak = "2.0-beta-2"
    const val glide = "4.9.0"
    const val baseAdapter = "0.1.3"
    const val androidVeil = "1.0.6"
    const val mockito = "3.0.0"
    const val mockitoKotlin = "2.1.0"
    const val mockWebServer = "3.14.0"
    const val espresso = "3.0.2"
    const val jaxbCore = "2.3.0.1"
    const val jaxbApi = "2.3.1"
    const val jaxbImpl = "2.3.2"
    const val kotlin_version = "1.3.61"
    const val retrofitVersion = "2.6.1"
    const val okHttpVersion = "3.10.0"
    const val daggerVersion = "2.25.2"
    const val rxVersion = "2.2.11"
    const val rxAndroidVersion = "2.1.1"
    const val lifecycleVersion = "2.2.0-rc01"
    const val room_version = "2.2.2"
}