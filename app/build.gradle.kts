plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cancellation_feature_seats_booking_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cancellation_feature_seats_booking_app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //RV
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.4.0")

    // ViewModel and LiveData
//    implementation ("androidx.lifecycle:lifecycle-extensions:$2.1.0")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$2.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:$2.8.0")





}