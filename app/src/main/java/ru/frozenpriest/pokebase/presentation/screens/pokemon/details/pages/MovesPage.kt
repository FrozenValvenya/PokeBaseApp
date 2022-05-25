package ru.frozenpriest.pokebase.presentation.screens.pokemon.details.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun MovesPokemon(pokemon: Pokemon, removeMove: (Move) -> Unit, addMove: () -> Unit) {
    var selectedMove by remember {
        mutableStateOf<Move?>(null)
    }

    if (selectedMove != null) {
        RemoveMoveDialog(selectedMove, { selectedMove = it }, removeMove)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
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
                MovesRow(move = move, modifier = Modifier, onClick = {
                    selectedMove = move
                })
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { addMove() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_new_move)
                )
            }
        }
    }
}

@Composable
private fun RemoveMoveDialog(
    selectedMove: Move?,
    onNewSelected: (Move?) -> Unit,
    removeMove: (Move) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onNewSelected(null) },
        title = {
            Text(text = stringResource(id = R.string.do_you_want_to_remove_it))
        },
        buttons = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        removeMove(selectedMove!!)
                        onNewSelected(null)
                    }
                ) {
                    Text(text = stringResource(R.string.delete))
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onNewSelected(null) }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        }
    )
}

@Composable
fun MovesHeader() {
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
fun MovesRow(modifier: Modifier, move: Move, onClick: (() -> Unit)? = null) {
    val isClickable = if (onClick != null) Modifier.clickable { onClick() } else Modifier
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(modifier)
            .then(isClickable),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = move.name,
            Modifier.weight(1.5f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = move.type.typeName,
            Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = move.type.getColor()
        )
        Text(
            text = move.category.name,
            Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = move.category.getColor()

        )
        Text(
            text = move.power?.toString() ?: "-",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = (move.accuracy)?.toInt().toString() + " %",
            Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
