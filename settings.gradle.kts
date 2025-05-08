pluginManagement {
    repositories {
        gradlePluginPortal()   // Required for Kotlin plugin
        google()               // Google repository for Kotlin and Firebase
        mavenCentral()         // Maven central for other dependencies
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Fail if project repos are used, prefer the ones from settings
    repositories {
        google()               // Google repository for dependencies
        mavenCentral()         // Maven central repository
        maven(url = "https://maven.fabric.io/public")
    }
}

rootProject.name = "MyApplication2"
include(":app")
