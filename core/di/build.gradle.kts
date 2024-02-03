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
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(projects.core.images)

    implementation(projects.core.dataGraphql)
    implementation(projects.core.dataKtor)
    implementation(projects.core.mvi)
    implementation(projects.core.datetime)
    implementation(projects.core.validation)

    implementation(projects.card)
    implementation(projects.filmInfo)
    implementation(projects.filmList)
    implementation(projects.tickets.shared)
    implementation(projects.tickets.schedule)
    implementation(projects.tickets.userInfo)
    implementation(projects.tickets.places)
}
