rootProject.name = "gradle-dependency-plugin"

include("platform", "example")

pluginManagement {
    repositories {
        // Keep portal to resolve common community plugins if needed
        gradlePluginPortal()
        mavenCentral()
        // Allow consumers to resolve this org’s convention plugin by ID from GitHub Packages
        maven {
            url = uri("https://maven.pkg.github.com/DevakumarJayaraman/gradle-dependency-plugin")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: (settings.extensions.extraProperties["gpr.user"] as String? ?: "")
                password = System.getenv("GITHUB_TOKEN") ?: (settings.extensions.extraProperties["gpr.key"] as String? ?: "")
            }
        }
    }
}

// Local development of the plugin without publishing
includeBuild("plugin")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}