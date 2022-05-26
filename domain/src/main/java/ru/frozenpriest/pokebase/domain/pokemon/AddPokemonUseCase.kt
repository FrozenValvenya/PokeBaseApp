package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.data.remote.model.PokemonDataRequest
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import javax.inject.Inject

interface AddPokemonUseCase {
    suspend fun submit(pokemonData: PokemonData): Result<String>
}

class AddPokemonUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : AddPokemonUseCase {
    override suspend fun submit(pokemonData: PokemonData): Result<String> {
        return pokeBaseRepository.submitNewPokemon(
            PokemonDataRequest(
                pokemonData.name,
                pokemonData.level,
                pokemonData.species.speciesId.toInt()
            )
        )
    }
}

data class PokemonData(
    val name: String,
    val level: Int,
    val species: SpeciesShort
)
