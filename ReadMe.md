# MovieDb

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