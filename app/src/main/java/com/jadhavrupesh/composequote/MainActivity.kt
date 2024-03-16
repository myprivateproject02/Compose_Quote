package com.jadhavrupesh.composequote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jadhavrupesh.composequote.presentation.ErrorScreen
import com.jadhavrupesh.composequote.presentation.LoadingScreen
import com.jadhavrupesh.composequote.presentation.MainViewModel
import com.jadhavrupesh.composequote.presentation.RandomQuoteScreen
import com.jadhavrupesh.composequote.ui.theme.ComposeQuoteTheme
import com.jadhavrupesh.composequote.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuoteTheme {
                val apiState = mainViewModel.response.value
                val scope = rememberCoroutineScope()
                LaunchedEffect(
                    key1 = true, block = { mainViewModel.getRandomQuote() })

                    Scaffold(
                        modifier = Modifier.navigationBarsPadding(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Compose Quote", fontFamily = FontFamily.Serif)
                                },
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    scope.launch {
                                        mainViewModel.getRandomQuote()
                                    }
                                },
                            ) {
                                AsyncImage(
                                    model = R.drawable.shuffle,
                                    contentDescription = "",
                                    modifier = Modifier.size(width = 16.dp, height = 16.dp)
                                )
                            }
                        },
                    ) { paddingValues ->
                        AnimatedContent(
                            targetState = apiState,
                            label = "animated_content",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            transitionSpec = {
                                fadeIn(
                                    animationSpec = tween(
                                        durationMillis = 800, easing = LinearEasing
                                    )
                                ) togetherWith fadeOut(
                                    animationSpec = tween(
                                        durationMillis = 800, easing = LinearEasing
                                    )
                                )

                            }
                        ) { result ->
                            when (result) {
                                is ApiState.Success -> {
                                    val quote = result.data.body()!!
                                    RandomQuoteScreen(quote = quote)
                                }

                                is ApiState.Failuer -> {
                                    ErrorScreen(
                                        error = result.error.message ?: "Something Went Wrong!"
                                    )
                                }

                                is ApiState.Loading -> {
                                    LoadingScreen()
                                }

                                else -> Unit
                            }

                        }
                    }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuoteTheme {
        Greeting("Android")
    }
}