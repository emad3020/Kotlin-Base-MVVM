import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.kotlin) apply false
  alias(libs.plugins.kotlin.ksp) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.navigation.safeargs) apply false
  alias(libs.plugins.ktlint) apply false
  alias(libs.plugins.dagger.hilt.android) apply false
  alias(libs.plugins.googleServices) apply false
}

buildscript {
  dependencies {
    classpath(libs.navigationSafeArgs)
  }
}

subprojects {
  apply(plugin = "org.jlleitschuh.gradle.ktlint") // To apply ktLint to all included modules

  configure<KtlintExtension> {
    debug.set(true)
  }
}


tasks.register("clean", Delete::class) {
  delete(rootProject.layout.buildDirectory)
}

tasks.register("installGitHooks", Copy::class) {
  from("${rootProject.rootDir}/pre-commit")
  into("${rootProject.rootDir}/.git/hooks")
}