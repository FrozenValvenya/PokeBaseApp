package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.ui.graphics.Color

@Suppress("ComplexMethod")
fun String.getPokemonTypeColor(): Color {
    return when (this) {
        "Normal" -> Color(168, 168, 125)
        "Fighting" -> Color(177, 61, 49)
        "Flying" -> Color(164, 145, 234)
        "Poison" -> Color(150, 70, 155)
        "Ground" -> Color(164, 145, 234)
        "Rock" -> Color(180, 161, 75)
        "Bug" -> Color(171, 184, 66)
        "Ghost" -> Color(108, 89, 148)
        "Steel" -> Color(184, 184, 206)
        "Fire" -> Color(225, 134, 68)
        "Water" -> Color(112, 143, 233)
        "Grass" -> Color(139, 198, 96)
        "Electric" -> Color(243, 209, 84)
        "Psychic" -> Color(230, 99, 136)
        "Ice" -> Color(166, 214, 215)
        "Dragon" -> Color(105, 59, 239)
        "Dark" -> Color(109, 89, 74)
        "Fairy" -> Color(226, 157, 172)
        "???" -> Color(117, 159, 145)

        else -> throw NotImplementedError()
    }
}
