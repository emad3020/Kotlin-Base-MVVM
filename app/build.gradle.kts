import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.navigation.safeargs)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.googleServices)
}

android {
  namespace = "com.mina_mikhail.base_mvvm"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.mina_mikhail.base_mvvm"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    debug{
//       resValue("string", "google_api_key", gradleLocalProperties(rootDir).getProperty("GOOGLE_API_KEY"))
      manifestPlaceholders["appName"] = "@string/app_name_debug"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_debug"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_debug_round"

      buildConfigField("String", "API_BASE_URL", "http://url.to.server/api/")
    }

    signingConfigs {
      create("releaseConfig") {
        storeFile = file(rootProject.file("key"))
        storePassword = "123456"
        keyAlias = "My-Key"
        keyPassword = "123456"
      }
    }

    release{
      signingConfig = signingConfigs.getByName("releaseConfig")

      isMinifyEnabled = true
      isShrinkResources = true

//      resValue("string", "google_api_key", gradleLocalProperties(rootDir).getProperty("GOOGLE_API_KEY"))
      manifestPlaceholders["appName"] = "@string/app_name"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_round"

      buildConfigField("String", "API_BASE_URL", "http://url.to.server/api/")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  buildFeatures {
    buildConfig = true
    dataBinding = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  // Networking
  implementation(libs.bundles.networking)

  // Utils

  implementation(libs.playServices)
  implementation(libs.localization)
  implementation(libs.multidex)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Project Modules
  implementation(projects.domain)
  implementation(projects.data)
  implementation(projects.presentation)
}