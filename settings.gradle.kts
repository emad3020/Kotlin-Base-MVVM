pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://jitpack.io")
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Kotlin-Base-MVVM"
include(":app")

// Core Modules
include(
  ":core:presentation",":core:network",":core:utils"
)

// Shared Core Modules
include(
  ":core:shared:actionChooser",
  ":core:shared:prettyPopUp",
  ":core:shared:boardingPager",
)

// Modules
include(":imagesSlider")
include(":core:config")
