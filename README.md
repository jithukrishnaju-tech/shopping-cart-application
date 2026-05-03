# Shopping Cart App

## Features Added
* **Product Listing:** View a list of items fetched from a remote API.
* **Shopping Cart:** Add items to the cart and view them in a dedicated cart screen.
* **Dynamic Summary:** Real-time calculation of subtotal, tax, and grand total.
* **Robust Error Handling:** Distinguishes between No Internet, API Timeouts, and HTTP errors (404, 500, etc.) with custom UI states.
* **Modern Splash Screen:** Integrates the official Android `core-splashscreen` API for a seamless launch experience.
* **Edge-to-Edge UI:** Properly handles window insets for seamless rendering under system status bars and navigation gesture spaces.

## Architecture
The application follows **Clean Architecture With MVVM** principles separated into three distinct layers:
1. **Domain Layer:** Contains enterprise logic, entities (`Item`, `CartItem`, `CartSummary`), and strict `UseCase` classes. Exposes clean `DomainException` to avoid leaking HTTP/Database exceptions to the UI.
2. **Data Layer:** Handles data retrieval and storage. Uses the **Repository Pattern** to act as a single source of truth between `Room` (local cache) and `Retrofit` (remote API). Includes an `ErrorMapper` to safely translate system errors to Domain errors.
3. **Presentation Layer:** Uses the **MVVM (Model-View-ViewModel)** pattern. ViewModels expose immutable `StateFlow` to the UI, strictly decoupled from framework-specific contexts.

## Tech Stack & Libraries
* **Language:** Kotlin
* **UI:** Jetpack Compose + Material 3
* **Navigation:** Compose Navigation
* **Dependency Injection:** Dagger Hilt (with KSP)
* **Asynchronous Programming:** Kotlin Coroutines & StateFlow/Flow
* **Networking:** Retrofit2, Gson Converter, OkHttp3 (with Logging Interceptor)
* **Local Database:** Room Database
* **Core APIs:** Lifecycle Viewmodel-Compose, AndroidX Core SplashScreen
