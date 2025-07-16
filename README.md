# Newsapp

## Overview

**Newsapp** is an Android application built with Kotlin and Jetpack Compose that displays the latest news headlines and articles by integrating with the [NewsAPI](https://newsapi.org/). The app allows users to view top headlines, search for news articles by keyword, and browse news by categories such as health, business, entertainment, general, science, sports, and technology.

## Features

- **Top Headlines:** Fetches and displays top news headlines for a selected country (default: US).
- **Keyword Search:** Allows users to search for news articles by entering a query term.
- **Category Browsing:** Users can filter news articles based on predefined categories.
- **Article Details:** Each article displays its author, title, content, publication date, source, description, and associated image.
- **Modern UI:** Uses Jetpack Compose for a responsive and dynamic user interface.
- **State Management:** Uses Kotlin coroutines and StateFlow for asynchronous data loading and error handling.
- **API Integration:** Connects to NewsAPI using Retrofit for network calls.

## Architecture

- **MVVM Pattern:** The app is structured around the Model-View-ViewModel architecture for separation of concerns and better maintainability.
    - **Model:** Data classes such as `Article`, `Source`, and `ApiResponse` represent news data.
    - **ViewModel:** `NewsAppViewModel` manages app state, performs API calls, and exposes state flows to the UI.
    - **Repository:** `NewRepo` handles API requests and error/loading state management.
    - **View/UI:** Composable screens (e.g., `HomeScreenUI`, `CatogryDetailScreenUI`) render news content and handle user interaction.

## How It Works

- **API Service:** Defined in `ApiService.kt`, with endpoints for `top-headlines` and `everything` search.
- **Networking:** Built with Retrofit, using Gson for JSON parsing, as configured in `ApiBuilder.kt`.
- **State Handling:** Each API call returns an `ApiState` indicating loading, error, or data.
- **Navigation:** Jetpack Compose navigation manages screen transitions between home, category, and article detail views.

## File Structure

```
app/
 └─ src/
     ├─ main/
     │   ├─ java/com/example/newsapp/
     │   │   ├─ data/      # Data models, repository, API builder, API service
     │   │   ├─ presentation/ # ViewModel, navigation routes, UI screens
     │   │   ├─ ui/theme/  # Compose theme definitions
     │   │   └─ MainActivity.kt # App entry point
     │   └─ res/           # Resources (images, layouts, etc.)
     ├─ test/              # Unit tests
     └─ androidTest/       # Instrumented tests
```

## Key Classes

- `MainActivity.kt`: Initializes the Compose UI and sets up ViewModel and navigation.
- `NewsAppViewModel.kt`: Handles all API calls, manages loading/error/data state.
- `NewRepo.kt`: Repository for encapsulating API logic.
- `ApiBuilder.kt`: Configures Retrofit instance for network communication.
- `ApiService.kt`: Defines API endpoints for fetching news.
- `Article.kt`, `Source.kt`, `ApiResponse.kt`: Data models for articles and sources.

## Usage

1. Clone this repository.
2. Obtain an API key from [NewsAPI](https://newsapi.org/).
3. Open the project in Android Studio.
4. Replace the API key in `ApiService.kt` if needed.
5. Build and run the app on an Android device or emulator.

## Dependencies

- Kotlin
- Jetpack Compose
- Retrofit
- Gson
- Kotlin Coroutines
- Coil (for image loading)

## Example API Usage

```kotlin
@GET("top-headlines")
suspend fun getHeadlines(
    @Query("country") country: String = "us",
    @Query("apiKey") apiKey: String = "YOUR_API_KEY"
): ApiResponse
```

## Tests

Unit and instrumented tests are included in the `test` and `androidTest` directories using JUnit.

## License

This project is licensed under the MIT License.

---

*For any questions or contributions, please open an issue or pull request.*
