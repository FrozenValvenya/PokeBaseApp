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
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Type
import ru.frozenpriest.pokebase.domain.pokemon.GetOwnedPokemonShortUseCase
import timber.log.Timber
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val getOwnedPokemonShortUseCase: GetOwnedPokemonShortUseCase

) : ViewModel() {
    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> get() = _selectedPokemon

    private val _moves = MutableLiveData<List<Move>>()
    val moves: LiveData<List<Move>> get() = _moves

    private val _pokemons = MutableLiveData<List<PokemonShort>>()
    val pokemons: LiveData<List<PokemonShort>> get() = _pokemons

    init {
        val bulba = Species(
            name = "Bulbasaur",
            hp = Stat.makeHP(85),
            attack = Stat.makeAttack(10),
            defence = Stat.makeDefence(20),
            spAttack = Stat.makeSpAttack(20),
            spDefence = Stat.makeSpDefence(20),
            speed = Stat.makeSpeed(20),
            types = listOf(Type.Grass, Type.Poison),
            height = 70,
            weight = 6.9f,
            possibleEvolutions = listOf(),
            image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
        )
        _selectedPokemon.value = Pokemon(
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
                possibleEvolutions = listOf(
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba,
                    bulba
                ),
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
    }

    fun loadMoves() {
        _moves.postValue(
            listOf(
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33),
            )
        )
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

    @Suppress("LongMethod")
    fun loadPokemons() = viewModelScope.launch {
        Timber.i("Loading pokemons")
        val result = getOwnedPokemonShortUseCase.getPokemon()
        Timber.i("Got pokemons, result is ${if (result.isSuccess) "success" else "failure"}")

        result.onSuccess {
            _pokemons.postValue(it)
        }
    }
}
