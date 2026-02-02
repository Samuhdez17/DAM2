package com.example.apirest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apirest.ui.theme.ApiRestTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiRestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val jsonPlaceholderApi: JsonPlaceholderApi =
    Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonPlaceholderApi::class.java)

@Composable
fun PostCard(modifier: Modifier = Modifier) {
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

    val lanzadorApi = rememberCoroutineScope()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        posts = jsonPlaceholderApi.getPosts()
    }

    LazyVerticalGrid (GridCells.Fixed(1)){
        items(posts) { post ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Text(
                            text = post.body,
                            style = MaterialTheme.typography.bodyMedium,
                        )

                        Button(
                            onClick = {
                                lanzadorApi.launch {
                                    val response = jsonPlaceholderApi.deletePost(post.id)

                                    if (response.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Post borrado correctamente",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        posts = jsonPlaceholderApi.getPosts()
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "Borrar",
                                modifier = Modifier.width(50.dp)
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostScreen(modifier: Modifier) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var uId by remember { mutableIntStateOf(0) }
    var post by remember { mutableStateOf(Post(0, 0, "", "")) }

    val lanzadorApi = rememberCoroutineScope()

    Column {
        val modifierRows = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)

        TextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Titulo") },
            modifier = modifierRows
        )

        TextField(
            value = body,
            onValueChange = { body = it },
            placeholder = { Text("Mensaje") },
            modifier = modifierRows
        )

        TextField(
            value = "",
            onValueChange = { uId = it.toInt() },
            placeholder = { Text("ID usuario") },
            modifier = modifierRows
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = """
                {
                    title: ${post.title}
                    body: ${post.body}
                    id: ${post.id}
                    userId: ${post.userId}
                }
            """.trimIndent()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                lanzadorApi.launch {
                    post = jsonPlaceholderApi.createPost(Post(-1, uId, title, body))
                }
            },
        ) {
            Text(text = "Enviar post")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiRestTheme {
        PostCard()
    }
}
