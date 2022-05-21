package ru.frozenpriest.pokebase.presentation.screens.pokemon.details.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.presentation.common.TextRow

@Composable
fun AboutPokemon(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
            .background(
                color = MaterialTheme.colors.surface
            ),
        verticalArrangement = Arrangement.Top
    ) {
        TextRow(
            name = stringResource(id = R.string.height),
            value = pokemon.species.height.toString()
        )
        TextRow(
            name = stringResource(id = R.string.weight),
            value = pokemon.species.weight.toString()
        )
    }
}
