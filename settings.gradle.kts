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

include(":login")
include(":film-list")
include(":film-info")
include(":card")

include(":root")
include(":tickets:shared")
include(":tickets:places")
include(":tickets:schedule")
include(":tickets:confirmation")
include(":tickets:user-info")
include(":tickets:root")

include(":core:auth")
include(":core:data-graphql")
include(":core:data-ktor")
include(":core:di")
include(":core:mvi")
include(":core:component")
include(":core:images")
include(":core:datetime")
include(":core:validation")