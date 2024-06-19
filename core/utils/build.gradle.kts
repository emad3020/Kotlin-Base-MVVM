plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.core.utils"
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

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  lint {
    quiet = true
    warningsAsErrors = true
    abortOnError = false
    disable += "Instantiatable"
  }
}

dependencies {

  // Appcompat
  implementation(libs.appCompat)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Utils
  implementation(libs.zxing)
  implementation(libs.serializationJson)
  implementation(libs.kotlin.serialization)
}