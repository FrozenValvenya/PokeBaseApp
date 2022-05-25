package ru.frozenpriest.pokebase.domain.model

import ru.frozenpriest.pokebase.data.remote.model.MoveResponse

data class Move(
    val id: String,
    val name: String,
    val type: Type,
    val category: Category,
    val power: Int?,
    val accuracy: Float?,
    val pp: Int,
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
