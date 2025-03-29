# Movie Search App

A modern Android application built with Jetpack Compose that allows users to search and view movie details using the TMDB API.

## Features

- Search movies by title
- View detailed movie information
- Responsive UI with Material Design 3
- Dark/Light theme support
- Offline caching
- Rating system with stars
- Website link integration

## Setup

1. Clone the repository
2. Get a TMDB API Key:
   - Go to [TMDB](https://www.themoviedb.org/)
   - Create an account or sign in
   - Go to your profile settings
   - Click on "API" in the left sidebar
   - Request an API key (choose "Developer" option)
   - Copy your API key

3. Add your API Key:
   - Open `local.properties` file in the project root
   - Add the following line:
     ```
     TMDB_API_KEY=your_api_key_here
     ```

4. Build and run the project

## Technical Stack

- Kotlin
- Jetpack Compose
- Hilt (Dependency Injection)
- Retrofit (Network calls)
- Coil (Image loading)
- Material Design 3
- Navigation Compose

## Version History

- v1.0: Initial release with basic movie search and details functionality

# first commit includes
add dependencies on build.gradle(app) :
* Retrofit + Gson (API)
* Coil-Compose (images)
* ViewModel + Coroutines (architecture)
- Structure des packages
- api key

# second commit (branch) includes
* Interface TMDbApi.kt (searchMovies + getMovieDetails)
* Modèles de données :
- MovieResponse.kt (pour /search/movie)
- MovieDetailsResponse.kt (pour /movie/{id})
- Gestion des erreurs :
* Classe ResultWrapper (Success/Error/Loading)
* Intercepteur réseau basique

#third & fourth commit (branch) includes
*boilerPlate  homePage movies 


#fifth commit (branch) includes
*refactor code with hilt 
*clean code with repository 
*externalise composable


#last commit detail movie
* detail movie