package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor() : ViewModel() {
    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> get() = _selectedPokemon

    init {
        _selectedPokemon.value = Pokemon(
            name = "Poke",
            species = "Seed",
            level = 5,
            hp = 24,
            attack = 10,
            defence = 20,
            spAttack = 20,
            spDefence = 20,
            speed = 20,
            types = listOf("Grass", "Poison"),
            height = 70,
            weight = 6.9f,
        )
    }
}

data class Pokemon(
    val name: String,
    val species: String,
    val level: Int,
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val spAttack: Int,
    val spDefence: Int,
    val speed: Int,
    val types: List<String>,
    val height: Int,
    val weight: Float,
)
