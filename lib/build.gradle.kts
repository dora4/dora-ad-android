plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

android {
    namespace = "dora.ad"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
}

kotlin {
    jvmToolchain(19)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.appcompat:appcompat-resources:1.7.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    implementation("com.github.dora4:dora:1.2.29")
    implementation("com.github.dora4:dcache-android:2.5.20")
    implementation("com.github.dora4:dview-titlebar:1.37")
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation("com.github.Justson.AgentWeb:agentweb-core:v5.0.0-alpha.1-androidx")
    implementation("io.github.youth5201314:banner:2.2.2")
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                from(components["release"])
                groupId = "com.github.dora4"
                artifactId = "dora-ad-android"
                version = "1.7"
            }
        }
    }
}