plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
}

android {
  namespace = "codes.core.shared.actionChooser"
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
}