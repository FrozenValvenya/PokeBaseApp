@file:OptIn(ExperimentalPagerApi::class)

package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.presentation.theme.BlackText
import ru.frozenpriest.pokebase.presentation.theme.BlackTextTransparent

@Composable
fun PokemonDetailsScreen(viewModel: PokemonDetailsViewModel) {
    val selectedPokemon = viewModel.selectedPokemon.observeAsState()
    selectedPokemon.value?.let { pokemon ->
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    PokemonStatsPager(pokemon)
                }
            }
        }
    }
}

@Composable
private fun PokemonStatsPager(pokemon: Pokemon) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(top = 48.dp)
    ) {

        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        val tabNames = listOf(
            R.string.about,
            R.string.base_stats,
            R.string.evolution,
            R.string.moves
        )

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            tabNames.forEachIndexed { index, nameId ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = nameId),
                            fontSize = 12.sp,
                            color = getTabTextColor(index, pagerState)
                        )
                    }
                )
            }
        }
        HorizontalPager(count = tabNames.size, state = pagerState) { page ->
            when (page) {
                0 -> {
                    AboutPokemon(pokemon = pokemon)
                }
                1 -> {
                    AboutPokemon(pokemon = pokemon)
                }
            }
        }
    }
}

@Composable
private fun getTabTextColor(
    index: Int,
    pagerState: PagerState
) = if (index == pagerState.currentPage) BlackText else BlackTextTransparent

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
            name = stringResource(id = R.string.species),
            value = pokemon.species
        )
        TextRow(
            name = stringResource(id = R.string.height),
            value = pokemon.height.toString()
        )
        TextRow(
            name = stringResource(id = R.string.weight),
            value = pokemon.weight.toString()
        )
    }
}

@Composable
private fun TextRow(name: String, value: String) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(2f),
            color = MaterialTheme.colors.secondaryVariant,
            text = name
        )
        Text(modifier = Modifier.weight(3f), color = MaterialTheme.colors.secondary, text = value)
    }
}
