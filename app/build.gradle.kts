import Versions.daggerVersion
import Versions.kotlin_version
import Versions.lifecycleVersion
import Versions.okHttpVersion
import Versions.retrofitVersion
import Versions.rxAndroidVersion
import Versions.rxVersion
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}
android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "com.cellfishpool.news"
        minSdkVersion(27)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding {
        isEnabled = true
    }
}
dependencies {
    //implementation fileTree(dir: ("libs(", include: [("*.jar("])
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // Dagger 2
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
    implementation("com.google.dagger:dagger-android-support:$daggerVersion")
    kapt("com.google.dagger:dagger-android-processor:$daggerVersion")

    // Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    // Timber
    implementation(
        "com.jakewharton.timber:timber:4.7.1"
    )

    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")

    //Rx
    implementation("io.reactivex.rxjava2:rxjava:$rxVersion")
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation("com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar")
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    //material design
    implementation("com.google.android.material:material:1.0.0")

    //picasso
    implementation("com.squareup.picasso:picasso:1.0.0")

    //paging
    implementation("androidx.paging:paging-runtime:2.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

    implementation("androidx.room:room-ktx:2.2.2")
    kapt("androidx.room:room-compiler:2.2.2")

    //robolectric
    testImplementation("org.robolectric:robolectric:4.3")
    testImplementation("androidx.test:core:1.2.0")
    testImplementation("androidx.test.ext:junit:1.1.0")

    //mockito
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC3")

    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.0.2")

    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    //Material Theme
    implementation("com.google.android.material:material:1.1.0-alpha03")

    //FragmentPreferenceCompat
    implementation("androidx.preference:preference:1.1.0")

    //RxJava2
    implementation("io.reactivex.rxjava2:rxjava:2.2.17")
    implementation("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")

    //Rx binding
    implementation("com.jakewharton.rxbinding2:rxbinding:2.2.0")

    //Pagination
    implementation("androidx.paging:paging-runtime:2.1.1")

    //Firebase SDK
    implementation ("com.google.firebase:firebase-crashlytics:17.0.0-beta01")
    implementation ("com.google.firebase:firebase-analytics:17.2.3")

}
