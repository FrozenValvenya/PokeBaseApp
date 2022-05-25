package ru.frozenpriest.pokebase.presentation.screens.pokemon.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import ru.frozenpriest.pokebase.R
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.pokemon.GetSpeciesUseCase
import ru.frozenpriest.pokebase.presentation.common.blackOrWhiteContentColor
import ru.frozenpriest.pokebase.presentation.common.getColor
import ru.frozenpriest.pokebase.presentation.theme.PokeBaseTheme

@Composable
fun AddNewPokemonScreen(viewModel: AddNewPokemonViewModel, navController: NavController) {
    val pokemonData by viewModel.selectedPokemon.observeAsState(PokemonData())
    val species by viewModel.species.observeAsState(emptyList())
    val isDataFinished by viewModel.readyToCreate.observeAsState(false)
    val status by viewModel.status.observeAsState()

    LaunchedEffect(key1 = null) {
        viewModel.getSpecies()
    }

    LaunchedEffect(key1 = status) {
        if (status == Status.Success) navController.popBackStack()
    }

    Scaffold {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_new_pokemon),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(32.dp)
            )
            NameField(pokemonData, viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            LevelField(pokemonData, viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            SpeciesSelector(species, pokemonData, viewModel)
            Button(
                onClick = { viewModel.submitPokemon() },
                enabled = isDataFinished,
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(text = stringResource(id = R.string.add))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColumnScope.SpeciesSelector(
    species: List<SpeciesShort>,
    pokemonData: PokemonData,
    viewModel: AddNewPokemonViewModel
) {
    LazyColumn(
        Modifier
            .weight(1f)
            .padding(vertical = 16.dp)
    ) {
        items(species) { species ->
            Card(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                backgroundColor = if (species.speciesId == pokemonData.species?.speciesId)
                    MaterialTheme.colors.primaryVariant
                else
                    MaterialTheme.colors.background,
                onClick = {
                    viewModel.setSpecies(species)
                }
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = species.name,
                        color = species.types.first().getColor(),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    species.types.forEach { type ->
                        Text(
                            text = type.name,
                            color = blackOrWhiteContentColor(background = type.getColor()),
                            fontSize = 10.sp,
                            modifier = Modifier
                                .padding(all = 8.dp)
                                .background(
                                    type.getColor(),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    AsyncImage(
                        modifier = Modifier.padding(all = 8.dp),
                        model = species.image,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun NameField(
    pokemonData: PokemonData,
    viewModel: AddNewPokemonViewModel
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = pokemonData.name ?: "",
        onValueChange = {
            viewModel.setName(it)
        },
        singleLine = true,
        label = {
            Text(text = stringResource(id = R.string.name))
        }
    )
}

@Composable
private fun LevelField(
    pokemonData: PokemonData,
    viewModel: AddNewPokemonViewModel
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = pokemonData.level?.toString() ?: "",
        onValueChange = {
            if (it.isDigitsOnly()) {
                viewModel.setLevel(if (it.isBlank()) null else it.toInt())
            }
        },
        singleLine = true,
        label = {
            Text(text = stringResource(id = R.string.level))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun AddNewPreview() {
    PokeBaseTheme {
        AddNewPokemonScreen(
            viewModel = AddNewPokemonViewModel(object : GetSpeciesUseCase {
                override suspend fun getSpecies(): Result<List<SpeciesShort>> {
                    return Result.success(emptyList())
                }
            }),
            navController = rememberNavController()
        )
    }
}
