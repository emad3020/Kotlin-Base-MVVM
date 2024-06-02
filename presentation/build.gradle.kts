plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.navigation.safeargs)
}

android {
  namespace = "com.mina_mikhail.base_mvvm.presentation"
  compileSdk = 34

  defaultConfig {
    minSdk = 21

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    release{
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
    dataBinding = true
  }
}

dependencies {

  // Support
  implementation(libs.bundles.androidSupport)

  // Arch Components
  implementation(libs.bundles.archComponents)

  // Kotlin Coroutines
  implementation(libs.coroutinesCore)
  implementation(libs.coroutinesAndroid)

  // UI
  implementation(libs.loadingAnimations)
  implementation(libs.alerter)
  implementation(libs.coil)

  // Utils
  implementation(libs.playServices)
  implementation(libs.localization)
  implementation(libs.permissions)
  implementation(libs.gson)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Map
  implementation(libs.map)
  implementation(libs.playServicesLocation)

  // Project Modules
  implementation(projects.domain)
  implementation(projects.prettyPopUp)
  implementation(projects.actionChooser)
  implementation(projects.appTutorial)
  implementation(projects.imagesSlider)
}