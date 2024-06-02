plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
}

android {
  namespace = "com.mina_mikhail.base_mvvm.data"
  compileSdk = 34

  defaultConfig {
    minSdk = 21
  }


  buildTypes {
    release {
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

}

dependencies {

  // Kotlin Coroutines
  implementation(libs.coroutinesCore)
  implementation(libs.coroutinesAndroid)

  // Networking
  implementation(libs.bundles.networking)

  implementation(libs.javaInject)

  // Project Modules
  implementation(projects.domain)
}