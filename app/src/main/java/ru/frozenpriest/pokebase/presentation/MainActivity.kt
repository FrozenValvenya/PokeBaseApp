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
import ru.frozenpriest.pokebase.presentation.screens.login.LoginRegisterScreen
import ru.frozenpriest.pokebase.presentation.screens.login.LoginRegisterViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.add.AddNewPokemonScreen
import ru.frozenpriest.pokebase.presentation.screens.pokemon.add.AddNewPokemonViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.battle.PokemonBattleScreen
import ru.frozenpriest.pokebase.presentation.screens.pokemon.battle.PokemonBattleViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.PokemonDetailsScreen
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.PokemonDetailsViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.owned.OwnedPokemonsScreen
import ru.frozenpriest.pokebase.presentation.screens.pokemon.owned.OwnedPokemonsViewModel
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeBaseTheme {
                val navController = rememberNavController()

                NavHost(
                    navController,
                    startDestination = NavigationDestination.LoginRegister.destination
                ) {
                    val component = AppComponentHolder.getComponent()

                    composable(NavigationDestination.LoginRegister.destination) {
                        val viewModel: LoginRegisterViewModel =
                            daggerViewModel(factory = component.getFactory())

                        LoginRegisterScreen(
                            viewModel = viewModel,
                            navController = navController,
                        )
                    }

                    composable(NavigationDestination.PokemonDetails.destination) {
                        val viewModel: PokemonDetailsViewModel =
                            daggerViewModel(factory = component.getFactory())

                        PokemonDetailsScreen(
                            viewModel = viewModel,
                            navController = navController,
                            it.arguments?.getString("id")!!
                        )
                    }

                    composable(NavigationDestination.OwnedPokemons.destination) {
                        val viewModel: OwnedPokemonsViewModel =
                            daggerViewModel(factory = component.getFactory())

                        OwnedPokemonsScreen(viewModel = viewModel, navController = navController)
                    }

                    composable(NavigationDestination.NewPokemon.destination) {
                        val viewModel: AddNewPokemonViewModel =
                            daggerViewModel(factory = component.getFactory())

                        AddNewPokemonScreen(viewModel = viewModel, navController = navController)
                    }

                    composable(NavigationDestination.PokemonBattle.destination) {
                        val viewModel: PokemonBattleViewModel =
                            daggerViewModel(factory = component.getFactory())

                        PokemonBattleScreen(
                            viewModel,
                            it.arguments?.getString("id1")!!,
                            it.arguments?.getString("id2")!!
                        )
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
