package ru.frozenpriest.pokebase.presentation.screens.pokemon.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.frozenpriest.pokebase.domain.model.Species
import javax.inject.Inject

class AddNewPokemonViewModel @Inject constructor() : ViewModel() {
    private val _selectedPokemon = MutableLiveData(PokemonData())
    val selectedPokemon: LiveData<PokemonData> get() = _selectedPokemon

    private val _species = MutableLiveData<List<Species>>()
    val species: LiveData<List<Species>> get() = _species

    val readyToCreate = MediatorLiveData<Boolean>().apply {
        addSource(_selectedPokemon) {
            this.value = it?.species != null && it.level != null && it.name != null
        }
    }

    private val _status = MutableLiveData(Status.Ready)
    val status: LiveData<Status> get() = _status

    init {
        _species.value = listOf()
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

    fun setSpecies(species: Species) {
        _selectedPokemon.value = _selectedPokemon.value?.copy(species = species)
    }

    fun submitPokemon() {
        _status.value = Status.Waiting
        // submits pokemon

        // then set status to true after result
        _status.value = Status.Success
    }
}

data class PokemonData(
    val name: String? = null,
    val level: Int? = null,
    val species: Species? = null
)
enum class Status {
    Ready, Waiting, Success
}
