// Apply necessary plugins for the project
plugins {
    kotlin("kapt") // Kotlin annotation processing plugin
    id("com.android.application") // Android application plugin
    id("org.jetbrains.kotlin.android") // Kotlin Android extension plugin
    id("dagger.hilt.android.plugin") // Dagger Hilt Android plugin
    id("androidx.navigation.safeargs.kotlin") // Navigation Safe Args plugin
}

// Android configuration block
android {
    namespace = "com.azmiradi.currency" // Package namespace for the app
    compileSdk = 34 // SDK version to compile against

    // Default configuration for the app
    defaultConfig {
        applicationId = "com.azmiradi.currency"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Test runner for instrumented tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Enable data binding features
    buildFeatures {
        dataBinding = true
    }

    // Build types configuration
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // Add more build types if needed (e.g., debug)
    }

    // Java and Kotlin compilation options
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Dependencies block for library dependencies
dependencies {
    // AndroidX libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Unit testing dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("app.cash.turbine:turbine:1.0.0")

    // For different screen sizes
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")


    // OK HTTP & Retrofit
    implementation("com.squareup.logcat:logcat:0.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation ("com.google.code.gson:gson:2.10.1")

    // Navigation components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
}