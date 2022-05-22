package ru.frozenpriest.pokebase.presentation.screens.pokemon.owned

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.presentation.NavigationDestination
import ru.frozenpriest.pokebase.presentation.common.getColor
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme
import ru.frozenpriest.pokebase.presentation.withId

@Composable
fun OwnedPokemonsScreen(
    viewModel: OwnedPokemonsViewModel,
    navController: NavController
) {
    val pokemonState = viewModel.pokemonFlow.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {
                        navController.navigate(NavigationDestination.NewPokemon.destination)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_new_pokemon)
                    )
                }
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            OwnedPokemons(pokemonState.value, navController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OwnedPokemons(
    pokemons: List<Pokemon>,
    navController: NavController
) {

    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item(span = { GridItemSpan(this.maxCurrentLineSpan) }) {
            Text(
                modifier = Modifier.padding(start = 8.dp, top = 32.dp, bottom = 32.dp),
                text = stringResource(id = R.string.owned_pokemon_header),
                style = MaterialTheme.typography.h1
            )
        }
        items(items = pokemons) { item ->
            PokemonItem(
                modifier = Modifier,
                pokemon = item,
                onClick = {
                    navController.navigate(
                        NavigationDestination.PokemonDetails.destination.withId(
                            item.id
                        )
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonItem(modifier: Modifier, pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = modifier,
        backgroundColor = pokemon.species.types.first().getColor(),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = pokemon.name,
                    color = Color.White
                )
                Text(
                    text = pokemon.species.name,
                    color = Color.White
                )
                pokemon.species.types.forEach { type ->
                    Text(
                        text = type.name,
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.species.image)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxSize(0.5f),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun OwnedPokemonsScreenPreview() {
    PokeBaseTheme {
        OwnedPokemonsScreen(
            viewModel = OwnedPokemonsViewModel(),
            navController = rememberNavController()
        )
    }
}
