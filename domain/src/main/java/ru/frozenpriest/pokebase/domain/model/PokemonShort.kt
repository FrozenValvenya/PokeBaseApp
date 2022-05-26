package ru.frozenpriest.pokebase.domain.model

import ru.frozenpriest.pokebase.data.remote.model.PokemonShortResponse

data class PokemonShort(
    val id: String,
    val name: String,
    val speciesName: String,
    val imageUrl: String,
    val types: List<Type>,
)

fun PokemonShortResponse.toPokemonShort(): PokemonShort {
    return PokemonShort(
        id = pokemonId,
        name = nickname,
        speciesName = species.name,
        imageUrl = species.image,
        types = listOfNotNull(species.primaryType, species.secondaryType).map { Type.valueOf(it) }
    )
}
