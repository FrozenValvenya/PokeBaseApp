package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Type

@Serializable
data class MoveResponse(
    val moveId: Int,
    val name: String,
    val type: String,
    val category: String,
    val power: Int?,
    val accuracy: Int?,
    val pp: Int,
    val description: String
)

fun MoveResponse.toMove(): Move {
    return Move(
        id = moveId.toString(),
        name = name,
        type = Type.valueOf(type),
        category = Category.valueOf(category),
        power = power,
        accuracy = accuracy?.toFloat(),
        pp = pp
    )
}
