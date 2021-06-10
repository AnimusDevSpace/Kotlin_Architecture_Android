plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.assignment.cleanarchitecture")
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility("VERSION_1_8")
        targetCompatibility("VERSION_1_8")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-8:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.activity:activity-ktx:1.2.3")
    implementation("androidx.fragment:fragment-ktx:1.3.4")

    //Hilt
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_version"
    implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hiltVersion")}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra.get("hiltVersion")}")

    //DI-dagger_2
//    implementation 'com.google.dagger:dagger-android:2.35.1'
//    kapt 'com.google.dagger:dagger-compiler:2.20'

    //LifeCycle
    implementation("androidx.lifecycle:lifecycle-common:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
//    implementation 'android.arch.lifecycle:extensions:2.2.0'
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("android.arch.lifecycle:viewmodel:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    //glide library
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    //round image view
    implementation("de.hdodenhof:circleimageview:3.1.0")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")


//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
}
