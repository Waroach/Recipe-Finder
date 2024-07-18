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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            // ... other libraries ...
            library("androidx-compose-bom", "androidx.compose:compose-bom:2023.03.00")
            library("androidx-compose-material3", "androidx.compose.material3:material3:1.1.0")
            // ... other Compose libraries ...
        }
    }
}

rootProject.name = "RecipeApp"
include(":app")
 