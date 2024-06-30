plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "codes.core.shared.prettyPopUp"
  compileSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")

  defaultConfig {

    minSdk = Integer.valueOf("${rootProject.extra.get("minSdk")}")
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

  lint {
    quiet = true
    abortOnError = false
    warningsAsErrors = true
    disable += "Instantiatable"
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  viewBinding.isEnabled = true
}

dependencies {
  implementation(libs.lifecycle)
  implementation(libs.materialDesign)

  // Utils
  implementation(libs.lottie)
}