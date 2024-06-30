plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.navigation.safeargs)
}

android {
  namespace = "com.core.presentation"
  compileSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")

  defaultConfig {
    minSdk = Integer.valueOf("${rootProject.extra.get("minSdk")}")

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      consumerProguardFiles("proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  viewBinding.isEnabled = true
  buildFeatures.buildConfig = true
}

dependencies {
  // Support
  api(libs.androidSupport)

  // Arch Components
  api(libs.lifeData)

  // UI
  api(libs.materialDesign)
  api(libs.bundles.navigationComponent)
  implementation(libs.alerter)
  implementation(libs.coil)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Utils
  implementation(libs.localization)
  api(libs.timberLogger)
  api(libs.android.sdp)
  api(libs.android.ssp)

  // Core Modules
  api(projects.core.utils)

  // Shared Libraries
  api(projects.core.shared.prettyPopUp)
  api(projects.core.shared.actionChooser)

  // Project Modules
  implementation(projects.imagesSlider)
}