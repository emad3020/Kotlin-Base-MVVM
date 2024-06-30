import com.android.build.api.variant.BuildConfigField


plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.android.kotlin)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.navigation.safeargs)
  alias(libs.plugins.dagger.hilt.android)
//  alias(libs.plugins.googleServices)
}

androidComponents {
  onVariants {
    it.buildConfigFields.apply {
      put(
        "API_BASE_URL",
        BuildConfigField(
          "String",
          "\"http://url.to.server/api/\"",
          "base Url"
        )
      )
    }
  }
}

android {
  namespace = "com.mina.base.mvvm"
  compileSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")

  defaultConfig {
    applicationId = "com.mina_mikhail.base_mvvm"
    minSdk = Integer.valueOf("${rootProject.extra.get("minSdk")}")
    targetSdk = Integer.valueOf("${rootProject.extra.get("compileSdk")}")
    versionCode = Integer.valueOf("${rootProject.extra.get("versionCode")}")
    versionName = rootProject.extra.get("versionName").toString()

    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    testInstrumentationRunner = "${rootProject.extra.get("testRunner")}"
  }

  buildTypes {
    debug {
//       resValue("string", "google_api_key", gradleLocalProperties(rootDir).getProperty("GOOGLE_API_KEY"))
      manifestPlaceholders["appName"] = "@string/app_name_debug"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_debug"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_debug_round"
    }

    signingConfigs {
      create("releaseConfig") {
        storeFile = file(rootProject.file("key"))
        storePassword = "123456"
        keyAlias = "My-Key"
        keyPassword = "123456"
      }
    }

    release {
      signingConfig = signingConfigs.getByName("releaseConfig")

      isMinifyEnabled = true
      isShrinkResources = true

//      resValue("string", "google_api_key", gradleLocalProperties(rootDir).getProperty("GOOGLE_API_KEY"))
      manifestPlaceholders["appName"] = "@string/app_name"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_round"
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
    buildConfig = true
    viewBinding = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  // Networking
  implementation(libs.bundles.networking)

  // Utils

//  implementation(libs.playServices)
  implementation(libs.localization)
  implementation(libs.multidex)

  // Hilt
  implementation(libs.hilt)
  ksp(libs.hiltDaggerCompiler)

  // Core Modules
  implementation(projects.core.shared.actionChooser)
  implementation(projects.core.shared.prettyPopUp)
  implementation(projects.core.shared.boardingPager)
  implementation(projects.core.network)
  implementation(projects.core.presentation)
  implementation(projects.core.utils)
}