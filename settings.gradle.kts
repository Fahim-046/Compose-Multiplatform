rootProject.name = "CMPBoilerplate"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
            mavenContent {
                includeGroupAndSubgroups("org.jetbrains.compose")
                includeGroupAndSubgroups("org.jetbrains.androidx")
                includeGroupAndSubgroups("org.jetbrains.androidx.navigation")
            }
        }
    }
}

include(":composeApp")