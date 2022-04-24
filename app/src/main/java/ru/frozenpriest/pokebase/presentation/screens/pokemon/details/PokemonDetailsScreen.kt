@file:OptIn(ExperimentalPagerApi::class)

package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.getStats
import ru.frozenpriest.pokebase.presentation.common.IntStatRow
import ru.frozenpriest.pokebase.presentation.common.TextRow
import ru.frozenpriest.pokebase.presentation.common.blackOrWhiteContentColor
import ru.frozenpriest.pokebase.presentation.common.getPokemonTypeColor
import ru.frozenpriest.pokebase.presentation.theme.BlackText
import ru.frozenpriest.pokebase.presentation.theme.BlackTextTransparent
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme

@Composable
fun PokemonDetailsScreen(viewModel: PokemonDetailsViewModel, navController: NavController) {
    val selectedPokemon = viewModel.selectedPokemon.observeAsState()
    selectedPokemon.value?.let { pokemon ->
        var dominantColor by remember {
            mutableStateOf(Color.White)
        }
        val colorAnimated = animateColorAsState(targetValue = dominantColor)
        val pokemonDrawable =
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.image)
                    .crossfade(true)
                    .build(),
                onSuccess = { state ->
                    viewModel.calculateDominantColor(
                        state.result.drawable,
                        onFinish = { color -> dominantColor = color }
                    )
                },
            )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DetailsTopBar(colorAnimated.value, navController)
            }
        ) {
            DetailsContent(colorAnimated.value, pokemon, pokemonDrawable)
        }
    }
}

@Composable
private fun DetailsContent(
    dominantColor: Color,
    pokemon: Pokemon,
    pokemonDrawable: AsyncImagePainter
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
    ) {
        PokemonNameAndTypes(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            pokemon,
            dominantColor
        )
        Image(
            painter = painterResource(id = R.drawable.pokeball_icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .align(Alignment.CenterEnd)
                .offset(y = (-100).dp),
            alignment = Alignment.BottomEnd,
            alpha = 0.1f,
            colorFilter = ColorFilter.tint(blackOrWhiteContentColor(background = dominantColor))
        )
        PokemonSheetWithImage(pokemon, pokemonDrawable)
    }
}

@Composable
private fun BoxScope.PokemonSheetWithImage(
    pokemon: Pokemon,
    pokemonDrawable: AsyncImagePainter
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .align(Alignment.BottomCenter),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        PokemonStatsPager(pokemon)
    }
    Image(
        painter = pokemonDrawable,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .align(Alignment.Center)
            .offset(y = (-100).dp)
    )
}

@Composable
private fun DetailsTopBar(
    dominantColor: Color,
    navController: NavController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(dominantColor)
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = blackOrWhiteContentColor(dominantColor)
            )
        }
        IconButton(onClick = { /*mark as favorite(dont know if this will be impl*/ }) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(
                    id = R.string.add_to_fav
                ),
                tint = blackOrWhiteContentColor(dominantColor)
            )
        }
    }
}

@Composable
fun PokemonNameAndTypes(modifier: Modifier, pokemon: Pokemon, dominantColor: Color) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.h1,
            color = blackOrWhiteContentColor(dominantColor)
        )
        Text(
            text = pokemon.species.name,
            style = MaterialTheme.typography.h2,
            color = blackOrWhiteContentColor(dominantColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {
            items(pokemon.species.types) { type ->
                Card(
                    backgroundColor = type.getPokemonTypeColor(),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(
                        text = type,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonStatsPager(pokemon: Pokemon) {
    Column(
        Modifier
            .fillMaxSize()
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
                    StatsPokemon(pokemon = pokemon)
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

@Preview
@Composable
fun DetailsPreview() {
    PokeBaseTheme {
        PokemonDetailsScreen(viewModel = PokemonDetailsViewModel(), rememberNavController())
    }
}
