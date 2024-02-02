package ru.sulgik.cinemaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import com.arkivanov.decompose.defaultComponentContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.sulgik.cinemaapp.ui.theme.CinemaAppTheme
import ru.sulgik.core.component.withDI
import ru.sulgik.core.datetime.DateTimeFormatter
import ru.sulgik.core.datetime.LocalDateTimeFormatter
import ru.sulgik.host.component.RootComponent
import ru.sulgik.host.component.RootUI
import ru.sulgik.uikit.LocalImageLoader

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = RootComponent(defaultComponentContext().withDI())
        val imageLoader = get<ImageLoader>()
        val dateTimeFormatter = get<DateTimeFormatter>()
        setContent {
            CinemaAppTheme {
                CompositionLocalProvider(
                    LocalImageLoader provides imageLoader,
                    LocalDateTimeFormatter provides dateTimeFormatter,
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RootUI(
                            component = component,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CinemaAppTheme {
        Greeting("Android")
    }
}