import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import java.util.Properties
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
    id("com.codingfeline.buildkonfig")
}

// Load local.properties
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                // Navigation
                implementation(libs.androidx.navigation.compose)

                // Koin
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)

                // DataStore
                implementation(libs.datastore)
                implementation(libs.datastore.preferences)

                // Coil - Only in commonMain
                implementation(libs.coil.compose)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            api(libs.koin.android)

            // Ktor - Android
            implementation("io.ktor:ktor-client-android:3.0.1")

            // Coil - Android with OkHttp
            implementation(libs.coil.network.okhttp)
        }

        iosMain.dependencies {
            // Ktor - iOS
            implementation("io.ktor:ktor-client-darwin:3.0.1")

            // Coil - iOS with Ktor
            implementation(libs.coil.network.ktor)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.fahimdev.cmpboilerplate"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.fahimdev.cmpboilerplate"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "org.fahimdev.cmpboilerplate"

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", localProperties.getProperty("api.base.url", ""))
        buildConfigField(STRING, "API_KEY", localProperties.getProperty("api.key", ""))
    }
}
dependencies {
    debugImplementation(compose.uiTooling)
}