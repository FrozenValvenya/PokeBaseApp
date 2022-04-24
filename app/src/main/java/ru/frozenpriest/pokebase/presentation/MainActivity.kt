package ru.frozenpriest.pokebase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.frozenpriest.pokebase.di.AppComponentHolder
import ru.frozenpriest.pokebase.di.daggerViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.PokemonDetailsScreen
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.PokemonDetailsViewModel
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeBaseTheme {
                val navController = rememberNavController()

                NavHost(
                    navController,
                    startDestination = NavigationDestination.PokemonDetails.destination
                ) {
                    val component = AppComponentHolder.getComponent()

                    composable(NavigationDestination.PokemonDetails.destination) {
                        val viewModel: PokemonDetailsViewModel =
                            daggerViewModel(factory = component.getFactory())

                        PokemonDetailsScreen(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeBaseTheme {
        Greeting("Android")
    }
}
