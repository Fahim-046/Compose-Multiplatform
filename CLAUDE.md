# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

### Development
- `./gradlew composeApp:assembleDebug` - Build Android debug APK
- `./gradlew composeApp:assembleRelease` - Build Android release APK
- `./gradlew composeApp:embedAndSignAppleFrameworkForXcode` - Build iOS framework for Xcode

### Testing
- `./gradlew composeApp:test` - Run unit tests for all variants
- `./gradlew composeApp:testDebugUnitTest` - Run debug unit tests
- `./gradlew composeApp:iosX64Test` - Run iOS simulator tests
- `./gradlew composeApp:iosSimulatorArm64Test` - Run iOS ARM64 simulator tests

### Verification
- `./gradlew composeApp:lint` - Run lint checks
- `./gradlew composeApp:lintDebug` - Run lint on debug variant
- `./gradlew composeApp:check` - Run all checks (tests + lint)

### Installation
- `./gradlew composeApp:installDebug` - Install debug APK on connected device

## Architecture

This is a Kotlin Multiplatform project using **Clean Architecture** with three main layers:

### Layer Structure
- **Data Layer**: `data/` - API clients, repositories, data sources, mappers
- **Domain Layer**: `domain/` - Use cases, models, repository interfaces
- **Presentation Layer**: `presentation/` - ViewModels, Composables, UI state management

### Key Architectural Patterns
- **MVVM**: ViewModels manage UI state using `BaseViewModel` with `BaseUiState` and `BaseEvent`
- **Dependency Injection**: Koin modules in `data/di/` and `presentation/di/`
- **Repository Pattern**: Repository interfaces in domain, implementations in data layer
- **Use Case Pattern**: Domain use cases encapsulate business logic

### Platform-Specific Code
- **Common**: `commonMain/` - Shared business logic and UI
- **Android**: `androidMain/` - Android-specific implementations
- **iOS**: `iosMain/` - iOS-specific implementations

Each platform has its own DI modules (e.g., `PlatformModule.android.kt`, `DataStoreModule.ios.kt`)

### Navigation
- Uses Jetpack Navigation Compose with `Screen` sealed class in `presentation/navigation/`
- Bottom navigation implemented in `BottomNavigationBar`

### State Management
- DataStore for preferences and settings
- Koin for dependency injection across all layers
- Custom `DataStoreManager` for centralized data storage operations

### Resource Management
- Compose Multiplatform resources in `composeResources/`
- String localization support for multiple languages (bn, no)
- BuildKonfig for managing build configuration and secrets

## Configuration Files

### Local Setup
- Copy API credentials to `local.properties`:
  ```
  api.base.url=your_base_url
  api.key=your_api_key
  ```

### Gradle Configuration
- Version catalog: `gradle/libs.versions.toml`
- Main build file: `composeApp/build.gradle.kts`
- Uses BuildKonfig plugin for managing secrets and configuration