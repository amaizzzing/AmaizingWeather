import java.util.*

private const val kotlinVersion = "1.4.32"
private const val kotlinCoreCtxVersion = "1.3.2"
private const val androidGradleVersion = "7.0.2"

//support libs
private const val appcompatVersion = "1.2.0"
private const val constraintLayoutVersion = "2.0.4"
private const val materialVersion = "1.2.1"
private const val fragment_version = "1.3.2"

//test libs
private const val junitVersion = "4.12"
private const val runnerVersion = "1.1.2"
private const val espressoVersion = "3.3.0"

//moxy
private const val moxyVersion = "2.2.1"
private const val moxyKtxVersion = "2.1.2"

//cicerone
private const val ciceroneVersion = "6.6"

//rx
private const val rxVersion = "3.0.0"

//retrofit
private const val retrofitVersion = "2.9.0"

//room
private const val roomVersion = "2.2.6"
private const val roomCompilerVersion = "2.3.0-rc01"

//dagger
private const val daggerVersion = "2.24"

//glide
private const val glideVersion = "4.12.0"

//mockito
private const val mockitoVerstion = "4.0.0"
private const val mockitoKotlinVerstion = "2.2.0"

//firebase
private const val firebaseFuncs = "20.0.0"
private const val firebaseMsg = "22.0.0"

//googlePlay
private const val playAds = "20.2.0"
private const val googleSrvcs = "4.3.8"

object Dependencies{
    object Android {
        val minSdkVersion = 21
        val targetSdkVersion = 30
        val compileSdkVersion = 30
        val applicationId = "com.amaizzzing.amaizingweather"
        val versionCode = 5
        val versionName = "1.0.4"
    }
    object Kotlin{
        val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        val kotlinCoreKtx = "androidx.core:core-ktx:$kotlinCoreCtxVersion"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        val googleServices = "com.google.gms:google-services:$googleSrvcs"
    }
    object SupportLibs {
        val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        val constraint_layout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        val material = "com.google.android.material:material:$materialVersion"
        val fragment =  "androidx.fragment:fragment-ktx:$fragment_version"
    }
    object TestLibs {
        val junit = "junit:junit:$junitVersion"
        val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        val runner = "androidx.test:runner:$runnerVersion"
    }
    object Moxy {
        val moxy = "com.github.moxy-community:moxy:$moxyVersion"
        val moxyKtx = "com.github.moxy-community:moxy-ktx:$moxyKtxVersion"
        val moxyAndroidx = "com.github.moxy-community:moxy-androidx:$moxyKtxVersion"
        val moxyCompiler = "com.github.moxy-community:moxy-compiler:$moxyKtxVersion"
    }
    object Cicerone {
        val cicerone = "com.github.terrakok:cicerone:$ciceroneVersion"
    }
    object Rx {
        val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rxVersion"
        val rxJava = "io.reactivex.rxjava3:rxjava:$rxVersion"
    }
    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"
    }
    object Room {
        val room = "androidx.room:room-runtime:$roomVersion"
        val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }
    object Dagger {
        val dagger = "com.google.dagger:dagger:$daggerVersion"
        val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    }
    object Glide {
        val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }
    object Mockito {
        val mockito = "org.mockito:mockito-core:$mockitoVerstion"
        val mockitoInline = "org.mockito:mockito-inline:$mockitoVerstion"
        val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVerstion"
    }
    object Firebase {
        val firebaseFunctions = "com.google.firebase:firebase-functions:$firebaseFuncs"
        val firebaseMessaging = "com.google.firebase:firebase-messaging:$firebaseMsg"
    }
    object GooglePlay {
        val playServicesAds = "com.google.android.gms:play-services-ads:$playAds"
    }

    @JvmStatic
    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.ROOT).contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}