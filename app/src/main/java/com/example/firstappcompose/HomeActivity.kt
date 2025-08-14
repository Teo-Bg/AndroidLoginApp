package com.example.firstappcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstappcompose.ui.theme.FirstappcomposeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username")

        val movies = listOf(
            Movie("The Shawshank Redemption", "Drama", 1994, 9.3),
            Movie("The Godfather", "Crime", 1972, 9.2),
            Movie("The Dark Knight", "Action", 2008, 9.0),
            Movie("Pulp Fiction", "Crime", 1994, 8.9),
            Movie("Inception", "Sci-Fi", 2010, 8.8),
            Movie("Fight Club", "Drama", 1999, 8.8),
            Movie("Forrest Gump", "Drama", 1994, 8.8),
            Movie("The Matrix", "Sci-Fi", 1999, 8.7),
            Movie("Interstellar", "Sci-Fi", 2014, 8.6),
            Movie("The Green Mile", "Drama", 1999, 8.6)
        )

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPref.getBoolean("darkTheme", false)
        setContent {
            FirstappcomposeTheme(darkTheme = isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MovieListScreen(username, movies) { selectedMovie ->
                        Toast.makeText(this, selectedMovie.title, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
@Composable
fun MovieListScreen(username: String?, movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Hello, ${username ?: "Guest"}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(movies) { movie ->
                MovieItem(movie, onMovieClick)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick(movie) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Genre: ${movie.genre}")
            Text(text = "Year: ${movie.year}")
            Text(text = "Rating: ${movie.rating}")
        }
    }
}