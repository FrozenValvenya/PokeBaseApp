package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Stats
import ru.frozenpriest.pokebase.domain.model.Type

@Serializable
data class PokemonResponse(
    val pokemonId: Int,
    val nickname: String,
    val species: SpeciesResponse,
    val level: Int,
    val stats: StatsResponse,
    val moves: List<MoveResponse>
)

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        this.pokemonId.toString(),
        this.nickname,
        this.level,
        this.species.toSpecies(),
        this.moves.map { it.toMove() },
        this.stats.toStats()
    )
}

fun SpeciesResponse.toSpecies(): Species {
    return Species(
        this.speciesId.toString(),
        this.name,
        listOfNotNull(primaryType, secondaryType).map { Type.valueOf(it) },
        this.evolutions.map { it.toSpecies() },
        this.weight,
        this.height,
        this.baseStats.toStats(),
        this.movePool.map { it.toMove() },
        this.image
    )
}

fun SpeciesShortResponse.toShort(): SpeciesShort {
    return SpeciesShort(
        speciesId = speciesId,
        name = name,
        types = listOfNotNull(primaryType, secondaryType).map { Type.valueOf(it) },
        image = image
    )
}

fun StatsResponse.toStats(): Stats {
    return Stats(
        Stat.makeHP(this.hp),
        Stat.makeAttack(atk),
        Stat.makeDefence(def),
        Stat.makeSpAttack(spa),
        Stat.makeSpDefence(spd),
        Stat.makeSpeed(spe)
    )
}
