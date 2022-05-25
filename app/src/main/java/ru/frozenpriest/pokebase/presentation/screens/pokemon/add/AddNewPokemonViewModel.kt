package ru.frozenpriest.pokebase.presentation.screens.pokemon.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.pokemon.AddPokemonUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetSpeciesUseCase
import ru.frozenpriest.pokebase.domain.pokemon.PokemonData
import timber.log.Timber
import javax.inject.Inject

class AddNewPokemonViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val addPokemonUseCase: AddPokemonUseCase
) : ViewModel() {
    private val _selectedPokemon = MutableLiveData(PokemonDataNullable())
    val selectedPokemon: LiveData<PokemonDataNullable> get() = _selectedPokemon

    private val _species = MutableLiveData<List<SpeciesShort>>()
    val species: LiveData<List<SpeciesShort>> get() = _species

    val readyToCreate = MediatorLiveData<Boolean>().apply {
        addSource(_selectedPokemon) {
            this.value = it?.species != null && it.level != null && it.name != null
        }
    }

    private val _status = MutableLiveData(Status.Ready)
    val status: LiveData<Status> get() = _status

    fun getSpecies() = viewModelScope.launch {
        Timber.i("Loading species")
        val result = getSpeciesUseCase.getSpecies()
        Timber.i("Got species, result is $result")

        result.onSuccess {
            _species.postValue(it)
        }
    }

    fun setName(name: String) {
        _selectedPokemon.value = _selectedPokemon.value?.copy(name = name)
    }

    fun setLevel(level: Int?) {
        if (level != null && level > 100) {
            return
        }
        _selectedPokemon.value = _selectedPokemon.value?.copy(level = level)
    }

    fun setSpecies(species: SpeciesShort) {
        _selectedPokemon.value = _selectedPokemon.value?.copy(species = species)
    }

    fun submitPokemon() = viewModelScope.launch {
        val pokemonData = _selectedPokemon.value
        if (pokemonData?.species != null && pokemonData.level != null && pokemonData.name != null) {
            _status.value = Status.Waiting
            // submits pokemon
            val result = addPokemonUseCase.submit(
                PokemonData(
                    pokemonData.name,
                    pokemonData.level,
                    pokemonData.species
                )
            )
            result.onSuccess {
                _status.value = Status.Success
            }
        }
    }
}

enum class Status {
    Ready, Waiting, Success
}

data class PokemonDataNullable(
    val name: String? = null,
    val level: Int? = null,
    val species: SpeciesShort? = null
)
