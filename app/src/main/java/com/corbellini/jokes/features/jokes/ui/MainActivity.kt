package com.corbellini.jokes.features.jokes.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.corbellini.jokes.features.jokes.ui.theme.JokesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeApp {
                    ProvideWindowInsets {
                        NavGraph()
                    }
            }
        }
    }

}

@Composable
fun ComposeApp(content: @Composable () -> Unit) {
    JokesAppTheme {
        content()
    }
}

