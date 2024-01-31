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

include(":features:film-info:ui")
include(":features:film-info:domain")
include(":features:film-info:data")
include(":features:film-info:presentation")
include(":features:film-info:component")


include(":features:root:component")

include(":core:data-graphql")
include(":core:di")
include(":core:mvi")
include(":core:component")
include(":core:images")