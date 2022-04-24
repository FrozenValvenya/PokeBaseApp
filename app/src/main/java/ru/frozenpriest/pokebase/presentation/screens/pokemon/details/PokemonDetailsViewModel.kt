package ru.frozenpriest.pokebase.presentation.screens.pokemon.details

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import timber.log.Timber
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor() : ViewModel() {
    private val _selectedPokemon = MutableLiveData<Pokemon>()
    val selectedPokemon: LiveData<Pokemon> get() = _selectedPokemon

    init {
        _selectedPokemon.value = Pokemon(
            image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
            name = "Poke",
            species = Species(
                name = "Bulbasaur",
                hp = Stat.makeHP(85),
                attack = Stat.makeAttack(10),
                defence = Stat.makeDefence(20),
                spAttack = Stat.makeSpAttack(20),
                spDefence = Stat.makeSpDefence(20),
                speed = Stat.makeSpeed(20),
                types = listOf("Grass", "Poison"),
            ),
            level = 5,
            height = 70,
            weight = 6.9f,
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
}
