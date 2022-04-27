package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.ui.graphics.Color
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Type

@Suppress("ComplexMethod")
fun Type.getColor(): Color {
    return when (this) {
        Type.Normal -> Color(168, 168, 125)
        Type.Fighting -> Color(177, 61, 49)
        Type.Flying -> Color(164, 145, 234)
        Type.Poison -> Color(150, 70, 155)
        Type.Ground -> Color(164, 145, 234)
        Type.Rock -> Color(180, 161, 75)
        Type.Bug -> Color(171, 184, 66)
        Type.Ghost -> Color(108, 89, 148)
        Type.Steel -> Color(184, 184, 206)
        Type.Fire -> Color(225, 134, 68)
        Type.Water -> Color(112, 143, 233)
        Type.Grass -> Color(139, 198, 96)
        Type.Electric -> Color(243, 209, 84)
        Type.Psychic -> Color(230, 99, 136)
        Type.Ice -> Color(166, 214, 215)
        Type.Dragon -> Color(105, 59, 239)
        Type.Dark -> Color(109, 89, 74)
        Type.Fairy -> Color(226, 157, 172)
        Type.Unknown -> Color(117, 159, 145)

        else -> throw NotImplementedError()
    }
}

fun Category.getColor(): Color {
    return when (this) {
        Category.Physical -> Color.Red
        Category.Status -> Color.LightGray
        Category.Special -> Color.Gray
    }
}
