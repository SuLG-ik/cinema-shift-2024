plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "ru.sulgik.core.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(projects.core.images)

    implementation(projects.core.dataGraphql)
    implementation(projects.core.mvi)
    implementation(projects.core.datetime)
    implementation(projects.core.validation)

    implementation(projects.features.card.presentation)

    implementation(projects.features.filmInfo.data)
    implementation(projects.features.filmInfo.domain)
    implementation(projects.features.filmInfo.presentation)
    implementation(projects.features.filmList.data)
    implementation(projects.features.filmList.domain)
    implementation(projects.features.filmList.presentation)

    implementation(projects.features.tickets.domain)
    implementation(projects.features.tickets.data)
    implementation(projects.features.tickets.presentation)
    implementation(projects.features.tickets.schedule.presentation)
    implementation(projects.features.tickets.places.presentation)
    implementation(projects.features.tickets.userInfo.presentation)
}
