package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.presentation.common.getColor

@Composable
fun MovesPokemon(pokemon: Pokemon) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            MovesHeader()
        }
        item {
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
        items(pokemon.moves) { move ->
            MovesRow(move)
        }
    }
}

@Composable
private fun MovesHeader() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.move_name),
            Modifier.weight(1.5f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.move_type),
            Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.move_category),
            Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.move_power),
            Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.move_accuracy),
            Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun MovesRow(move: Move) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = move.name,
            Modifier.weight(1.5f),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = move.type.typeName,
            Modifier.weight(1f),
            color = move.type.getColor()
        )
        Text(
            text = move.category.name,
            Modifier.weight(1f),
            color = move.category.getColor()

        )
        Text(
            text = move.power?.toString() ?: "-",
            Modifier.weight(1f)
        )
        Text(
            text = (move.accuracy * 100).toInt().toString() + " %",
            Modifier.weight(1f)
        )
    }
}
