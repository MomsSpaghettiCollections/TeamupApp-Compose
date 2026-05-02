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
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TeamupApp-Compose"


include(":app")
include(":common:model")
include(":designsystem")
include(":feature:main:domain")
include(":feature:main:data")
include(":feature:main:presentation")
include(":feature:onboarding:domain")
include(":feature:onboarding:data")
include(":feature:onboarding:presentation")
include(":feature:dashboard:domain")
include(":feature:dashboard:presentation")
include(":feature:dashboard:data")
include(":storage:datastore")
include(":storage:database")
include(":feature:transaction:domain")
include(":feature:transaction:data")
include(":feature:transaction:presentation")
include(":network:firebase-auth")
include(":network:firebase-database")
