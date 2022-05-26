package ru.frozenpriest.pokebase.presentation.screens.pokemon.details.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Pokemon

@ExperimentalFoundationApi
@Composable
fun EvolutionPokemon(pokemon: Pokemon) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(100.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        modifier = Modifier.padding(8.dp)
    ) {
        items(pokemon.species.evolutions) { evolution ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(evolution.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(
                        id = R.string.possible_evolution,
                        evolution.name
                    )
                )
                Text(text = evolution.name)
            }
        }
    }
}
