package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.model.Type

@Serializable
data class PokemonShortResponse(
    val pokemonId: String,
    val nickname: String,
    val species: SpeciesShortResponse,
    val level: Int
)

@Serializable
data class SpeciesShortResponse(
    val speciesId: String,
    val name: String,
    val primaryType: String,
    val secondaryType: String?,
    val image: String
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
