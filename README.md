# CMP Boilerplate

A modern Kotlin Multiplatform project demonstrating best practices for building cross-platform Android and iOS applications with shared code. This boilerplate features a movie browsing application showcasing real-world implementation patterns.

## ✨ Features

- **🌐 Kotlin Multiplatform**: Shared business logic and data layer across platforms
- **🎨 Compose Multiplatform**: Unified UI development with Jetpack Compose
- **🏗️ Clean Architecture**: Well-structured layers (`data`, `domain`, `presentation`)
- **💉 Dependency Injection**: Koin for modular dependency management
- **🌐 Networking**: Ktor client for HTTP operations
- **💾 Data Persistence**: DataStore for preferences and local storage
- **🖼️ Image Loading**: Coil for efficient image loading and caching
- **🧭 Navigation**: Jetpack Navigation Compose with type-safe routing
- **🌙 Theming**: Light/Dark theme support with system preference detection
- **🌍 Localization**: Multi-language support (English, Bengali, Norwegian)
- **📱 Platform-specific**: Native implementations where needed

## 🏗️ Architecture

This project follows **Clean Architecture** principles with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│              Presentation              │  ← UI Layer (Compose, ViewModels)
├─────────────────────────────────────────┤
│                Domain                  │  ← Business Logic (Use Cases, Models)
├─────────────────────────────────────────┤
│                 Data                   │  ← Data Layer (Repos, API, Storage)
└─────────────────────────────────────────┘
```

### Layer Responsibilities

- **🎨 Presentation Layer**: UI components, ViewModels, navigation, and user interaction handling
- **💼 Domain Layer**: Business logic, use cases, and domain models (platform-agnostic)
- **💾 Data Layer**: Data sources, repositories, API clients, and data mapping

### Key Patterns

- **MVVM**: ViewModels manage UI state using reactive patterns
- **Repository Pattern**: Abstracts data sources behind interfaces
- **Use Case Pattern**: Encapsulates business logic in reusable components
- **Dependency Injection**: Modular architecture with Koin

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| **Language** | [Kotlin](https://kotlinlang.org/) |
| **UI Framework** | [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) |
| **Dependency Injection** | [Koin](https://insert-koin.io/) |
| **Networking** | [Ktor](https://ktor.io/) |
| **Local Storage** | [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) |
| **Image Loading** | [Coil](https://coil-kt.github.io/coil/) |
| **Navigation** | [Jetpack Navigation](https://developer.android.com/guide/navigation) |
| **Build Configuration** | [BuildKonfig](https://github.com/yshrsmz/BuildKonfig) |

## 🚀 Getting Started

### Prerequisites

- **For Android**: Android Studio Arctic Fox or later
- **For iOS**: macOS with Xcode 14+ installed
- **JDK**: Java 17 or later
- **Kotlin**: 2.2.0 (handled by Gradle)

### Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd CMP\ Boilerplate
   ```

2. **Configure API keys** (optional)
   Create a `local.properties` file in the root directory:
   ```properties
   api.base.url=your_movie_api_base_url
   api.key=your_api_key
   ```

### Build and Run

#### 🤖 Android
```bash
# Using Android Studio
# Open the project and run the `composeApp` configuration

# Using command line
./gradlew composeApp:assembleDebug
./gradlew composeApp:installDebug  # Install on connected device
```

#### 🍎 iOS
```bash
# Build the iOS framework
./gradlew composeApp:embedAndSignAppleFrameworkForXcode

# Open in Xcode
open iosApp/iosApp.xcworkspace
# Select simulator/device and run
```

### Development Commands

```bash
# Run tests
./gradlew composeApp:test

# Run lint checks
./gradlew composeApp:lint

# Run all checks
./gradlew composeApp:check
```

## 📁 Project Structure

```
CMP Boilerplate/
├── composeApp/                    # Shared multiplatform module
│   ├── src/
│   │   ├── commonMain/           # Shared code for all platforms
│   │   │   ├── kotlin/
│   │   │   │   └── org/fahimdev/cmpboilerplate/
│   │   │   │       ├── data/     # Data layer (repos, API, storage)
│   │   │   │       ├── domain/   # Business logic (use cases, models)
│   │   │   │       └── presentation/ # UI layer (screens, ViewModels)
│   │   │   └── composeResources/ # Shared resources (strings, images)
│   │   ├── androidMain/          # Android-specific implementations
│   │   └── iosMain/              # iOS-specific implementations
│   └── build.gradle.kts          # Module configuration
├── iosApp/                       # iOS application wrapper
│   └── iosApp.xcworkspace        # Xcode workspace
├── gradle/                       # Gradle configuration
│   └── libs.versions.toml        # Version catalog
└── build.gradle.kts              # Root build configuration
```

### Key Directories

- **`data/`**: Repositories, API services, data sources, and mappers
- **`domain/`**: Use cases, domain models, and repository interfaces
- **`presentation/`**: UI screens, ViewModels, navigation, and components
- **`composeResources/`**: Shared assets, strings, and localization files

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy coding! 🚀**
