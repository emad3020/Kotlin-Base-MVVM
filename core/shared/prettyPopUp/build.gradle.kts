plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "codes.core.shared.prettyPopUp"
  compileSdk = 34

  defaultConfig {

    minSdk = 21
  }

  buildTypes {
    debug {
      isMinifyEnabled = false
    }

    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  lint {
    quiet = true
    abortOnError = false
    warningsAsErrors = true
    disable += "Instantiatable"
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  buildFeatures {
    viewBinding = true
  }

  viewBinding.isEnabled = true
}

dependencies {
  implementation(libs.lifecycle)
  implementation(libs.materialDesign)

  // Utils
  implementation(libs.lottie)
}