package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.getStats
import ru.frozenpriest.pokebase.presentation.common.IntStatRow

@Composable
fun StatsPokemon(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
            .background(
                color = MaterialTheme.colors.surface
            ),
        verticalArrangement = Arrangement.Top,
    ) {
        pokemon.species.getStats().forEach { stat ->
            IntStatRow(stat = stat, limit = 100) // later should be changed maybe
        }
    }
}
