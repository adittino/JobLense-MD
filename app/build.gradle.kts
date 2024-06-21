plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.project.joblense"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.project.joblense"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation ("com.loopj.android:android-async-http:1.4.9")

    implementation ("androidx.datastore:datastore-preferences:1.1.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")

    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")

    implementation ("androidx.camera:camera-camera2:1.4.0-beta01")
    implementation ("androidx.camera:camera-lifecycle:1.4.0-beta01")
    implementation( "androidx.camera:camera-view:1.4.0-beta01")

    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")

    implementation ("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.room:room-paging:2.7.0-alpha03")
    implementation ("androidx.paging:paging-runtime-ktx:3.3.0")


    implementation ("androidx.room:room-ktx:2.7.0-alpha03")
    kapt ("androidx.room:room-compiler:2.6.1")

    //Compressor
    implementation ("id.zelory:compressor:3.0.1")
}