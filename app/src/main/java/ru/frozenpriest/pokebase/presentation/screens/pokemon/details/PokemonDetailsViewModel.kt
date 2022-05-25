package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.pokemon.GetMovesUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetOwnedPokemonShortUseCase
import timber.log.Timber
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val getOwnedPokemonShortUseCase: GetOwnedPokemonShortUseCase,
    private val getMovesUseCase: GetMovesUseCase

) : ViewModel() {
    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> get() = _selectedPokemon

    private val _moves = MutableLiveData<List<Move>>()
    val moves: LiveData<List<Move>> get() = _moves

    private val _pokemons = MutableLiveData<List<PokemonShort>>()
    val pokemons: LiveData<List<PokemonShort>> get() = _pokemons

    fun loadMoves() = viewModelScope.launch {
        Timber.i("Loading moves")
        val result = getMovesUseCase.getMoves(selectedPokemon.value!!.species)
        Timber.i("Got moves, result is $result")

        result.onSuccess {
            _moves.postValue(it)
        }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(
            Bitmap.Config.ARGB_8888,
            true
        )
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.let {
                onFinish(Color(it.rgb))
                Timber.i("Vibrant color should be ready $it")
            }
        }
    }

    @Suppress("UnusedPrivateMember")
    fun setId(pokemonId: String) {
        // later should be used on flow
    }

    fun removeMove(move: Move) {
        Timber.e("Remove move $move")
    }

    fun addMove(move: Move) {
        Timber.e("Add move $move")
    }

    fun loadPokemons() = viewModelScope.launch {
        Timber.i("Loading pokemons")
        val result = getOwnedPokemonShortUseCase.getPokemon()
        Timber.i("Got pokemons, result is ${if (result.isSuccess) "success" else "failure"}")

        result.onSuccess {
            _pokemons.postValue(it)
        }
    }
}
