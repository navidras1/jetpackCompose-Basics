pluginManagement {
    repositories {
//        google()
//        mavenCentral()
//        gradlePluginPortal()
        maven("https://maven.myket.ir")
        //maven("https://mirror.abrha.net/repository/maven/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        google()
//        mavenCentral()
        maven("https://maven.myket.ir")
        //maven("https://mirror.abrha.net/repository/maven/")

    }
}

rootProject.name = "MarketSuperApplication"
include(":app")
include(":MyKotlin")
