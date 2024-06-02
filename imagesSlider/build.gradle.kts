plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "codes.mina_mikhail.images_slider"
  compileSdk = 34

  defaultConfig {
    minSdk = 21
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles (getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
  implementation(libs.photoView)
  implementation(libs.androidSwiperefreshlayout)
  implementation(libs.glide)
  ksp(libs.glideCompiler)
}