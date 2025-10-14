# CMP Boilerplate

This is a Kotlin Multiplatform project that serves as a boilerplate for building Android and iOS applications with shared code. The project demonstrates a simple movie browsing application.

## Features

*   **Kotlin Multiplatform:** Shared business logic and data layer for Android and iOS.
*   **Compose Multiplatform:** Shared UI for Android and iOS using Jetpack Compose.
*   **Clean Architecture:** The project follows a clean architecture pattern with `data`, `domain`, and `presentation` layers.
*   **Dependency Injection:** Using Koin for managing dependencies.
*   **Networking:** Using Ktor for making network requests.
*   **Data Persistence:** Using DataStore for simple key-value data storage.
*   **Image Loading:** Using Coil for loading images from the network.
*   **Navigation:** Using Jetpack Navigation Compose for navigating between screens.
*   **Light/Dark Theme:** The app supports both light and dark themes.

## Architecture

The project is divided into three main layers:

*   **Data:** This layer is responsible for providing data to the application. It includes data sources (local and remote), repositories, and mappers.
*   **Domain:** This layer contains the business logic of the application. It includes use cases and models.
*   **Presentation:** This layer is responsible for the UI of the application. It includes ViewModels, Composables (screens), and navigation.

## Technologies Used

*   [Kotlin](https://kotlinlang.org/)
*   [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
*   [Koin](https://insert-koin.io/)
*   [Ktor](https://ktor.io/)
*   [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
*   [Coil](https://coil-kt.github.io/coil/)
*   [Jetpack Navigation](https://developer.android.com/guide/navigation)

## How to Build and Run

### Android

To run the application on Android, you can open the project in Android Studio and run the `composeApp` configuration.

### iOS

To run the application on iOS, you need to have a Mac with Xcode installed.

1.  Open a terminal and navigate to the project's root directory.
2.  Run the following command to build the Xcode project:
    ```bash
    ./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
    ```
3.  Open the `iosApp/iosApp.xcworkspace` file in Xcode.
4.  Select a simulator or a connected device and run the project.

## Project Structure

*   `composeApp`: This is the main module that contains the shared code for both Android and iOS.
    *   `commonMain`: Contains the common code for all platforms.
    *   `androidMain`: Contains the Android-specific code.
    *   `iosMain`: Contains the iOS-specific code.
*   `iosApp`: Contains the iOS application.
*   `gradle`: Contains the Gradle wrapper and version catalog.
