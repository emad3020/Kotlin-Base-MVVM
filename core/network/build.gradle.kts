plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "com.core.network"
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

  implementation(libs.coreKtx)

  // Networking
  implementation(libs.bundles.networking)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Utils
  api(libs.kotlin.serialization)
  api(libs.serializationJson)
}