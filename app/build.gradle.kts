import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Dependencies.Android.applicationId
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "WEATHER_API_KEY",
            project.property("WEATHER_API_KEY").toString()
        )
        buildConfigField(
            "String",
            "GEONAMES_API_KEY",
            project.property("GEONAMES_API_KEY").toString()
        )
    }
    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.kotlin_std)
    implementation(Dependencies.Kotlin.kotlinCoreKtx)
    implementation(Dependencies.SupportLibs.appcompat)
    implementation(Dependencies.SupportLibs.constraint_layout)
    implementation(Dependencies.SupportLibs.material)
    implementation(Dependencies.SupportLibs.fragment)
    implementation(Dependencies.Moxy.moxy)
    implementation(Dependencies.Moxy.moxyKtx)
    implementation(Dependencies.Moxy.moxyAndroidx)
    implementation(Dependencies.Cicerone.cicerone)
    implementation(Dependencies.Rx.rxJava)
    implementation(Dependencies.Rx.rxAndroid)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitAdapterRxJava)
    implementation(Dependencies.Retrofit.retrofitConverterGson)
    implementation(Dependencies.Room.room)
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Glide.glide)
    implementation(Dependencies.Firebase.firebaseFunctions)
    implementation(Dependencies.GooglePlay.playServicesAds)
    kapt(Dependencies.Room.roomCompiler)
    kapt(Dependencies.Moxy.moxyCompiler)
    kapt(Dependencies.Dagger.daggerCompiler)

    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.core)
    testImplementation(Dependencies.TestLibs.junitExt)
    testImplementation(Dependencies.TestLibs.junitTruth)
    androidTestImplementation(Dependencies.TestLibs.junitExt)
    androidTestImplementation(Dependencies.TestLibs.runner)
    androidTestImplementation(Dependencies.TestLibs.testRules)

    androidTestImplementation(Dependencies.TestLibs.espresso)
    androidTestImplementation(Dependencies.TestLibs.espressoCore)
    testImplementation(Dependencies.TestLibs.espressoIntents)
    testImplementation(Dependencies.TestLibs.espressoCore)
    debugImplementation(Dependencies.TestLibs.espressoContrib)

    testImplementation(Dependencies.TestLibs.mockitoCore)
    testImplementation(Dependencies.TestLibs.mockitoInline)
    testImplementation(Dependencies.TestLibs.mockitoKotlin)

    testImplementation(Dependencies.TestLibs.robolectric)

    androidTestImplementation(Dependencies.TestLibs.uiAutomator)

    debugImplementation(Dependencies.TestLibs.fragmentTest)
}
