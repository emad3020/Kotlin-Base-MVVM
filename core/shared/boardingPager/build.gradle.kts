plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.dagger.hilt.android)
}

android {
  namespace = "core.shared.boardingPager"
  compileSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")

  defaultConfig {
    minSdk = Integer.valueOf("${rootProject.extra.get("minSdk")}")
  }

  buildTypes {
    release {
      isMinifyEnabled = true
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
    viewBinding = true
  }
}

dependencies {
  implementation(libs.lifecycle)
  implementation(libs.materialDesign)
  implementation(libs.coroutinesCore)
  implementation(libs.coroutinesAndroid)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  
  // Core
  implementation(projects.core.presentation)
  implementation(projects.core.utils)


  //Lottie
  implementation(libs.lottie)
}