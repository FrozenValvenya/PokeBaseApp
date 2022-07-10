package ru.frozenpriest.pokebase.domain.model

data class Move(
    val id: String,
    val name: String,
    val type: Type,
    val category: Category,
    val power: Int?,
    val accuracy: Float?,
    val pp: Int,
)
