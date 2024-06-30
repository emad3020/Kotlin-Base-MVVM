plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.core.config"
    compileSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")

    defaultConfig {
        minSdk = Integer.valueOf("${rootProject.extra.get("minSdk")}")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

  buildFeatures.buildConfig = true

}

dependencies {

  implementation(libs.coreKtx)
  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)
}