package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Type
import timber.log.Timber
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor() : ViewModel() {
    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> get() = _selectedPokemon

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
                image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
            ),
            level = 5,
            height = 70,
            weight = 6.9f,
            moves = listOf(
                Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33)
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
}
