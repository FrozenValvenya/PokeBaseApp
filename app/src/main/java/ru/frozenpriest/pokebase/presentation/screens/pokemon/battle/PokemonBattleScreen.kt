package ru.frozenpriest.pokebase.presentation.screens.pokemon.battle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Type
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.pages.MovesHeader
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.pages.MovesRow
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme
import ru.frozenpriest.pokebase.presentation.theme.StatGood

@Composable
fun PokemonBattleScreen(navController: NavController) {
    var pokemon1 by remember {
        mutableStateOf(
            PokemonInBattle(
                54,
                Pokemon(
                    id = "r3r32r3r",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Grass, Type.Poison),
                        possibleEvolutions = listOf(),
                        height = 70,
                        weight = 6.9f,
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,

                    moves = listOf(
                        Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                        Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                        Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33)
                    )
                )
            )
        )
    }

    var pokemon2 by remember {
        mutableStateOf(
            PokemonInBattle(
                24,
                Pokemon(
                    id = "r3r32r3r",
                    name = "Poke333",
                    species = Species(
                        name = "Bulbasaur333",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Grass, Type.Poison),
                        possibleEvolutions = listOf(),
                        height = 70,
                        weight = 6.9f,
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,

                    moves = listOf(
                        Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                        Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                        Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33)
                    )
                )
            )
        )
    }
    Scaffold(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(StatGood),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.pokemon_battle),
                    style = MaterialTheme.typography.h1,
                    color = Color.White,
                    modifier = Modifier.padding(32.dp)
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                ) {
                    PokemonWithHealthRow(pokemon1, false)
                    PokemonWithHealthRow(pokemon2, true)
                }
            }
            MoveSelector(pokemon1) { move ->
                // viewmodel -> calculate damage,
                // give turn to next
            }
        }
    }
}

@Composable
fun MoveSelector(pokemon: PokemonInBattle, onMoveSelected: (Move) -> Unit) {
    Card(modifier = Modifier.padding(bottom = 48.dp)) {
        LazyColumn {
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

            items(pokemon.pokemon.moves) { move ->
                MovesRow(
                    move = move,
                    modifier = Modifier,
                    onClick = { onMoveSelected(move) }
                )
            }
        }
    }
}

@Composable
fun ColumnScope.PokemonWithHealthRow(pokemon: PokemonInBattle, inverted: Boolean) {
    CompositionLocalProvider(LocalLayoutDirection provides if (inverted) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(modifier = Modifier.fillMaxWidth(0.4f)) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = pokemon.hp.toString())
                    LinearProgressIndicator(
                        progress = pokemon.hp / pokemon.pokemon.species.hp.value.toFloat()
                    )
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.pokemon.species.image)
                    .build(),
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .scale(scaleX = if (inverted) -1f else 1f, scaleY = 1f),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PokeBattlePreview() {
    PokeBaseTheme {
        PokemonBattleScreen(navController = rememberNavController())
    }
}

data class PokemonInBattle(
    var hp: Int,
    val pokemon: Pokemon
)
