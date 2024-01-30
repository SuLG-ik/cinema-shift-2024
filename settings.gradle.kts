pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CinemaApp"
include(":app")
include(":uikit")

include(":features:film-list:ui")
include(":features:film-list:domain")
include(":features:film-list:data")
include(":features:film-list:presentation")
include(":features:film-list:component")

include(":core:data-graphql")
include(":core:di")
include(":core:mvi")
include(":core:component")