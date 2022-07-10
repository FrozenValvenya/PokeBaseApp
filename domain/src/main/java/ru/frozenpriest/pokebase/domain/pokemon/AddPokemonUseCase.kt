package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import javax.inject.Inject

interface AddPokemonUseCase {
    suspend fun submit(pokemonData: PokemonData): Result<String>
}

class AddPokemonUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : AddPokemonUseCase {
    override suspend fun submit(pokemonData: PokemonData): Result<String> {
        return pokeBaseRepository.submitNewPokemon(pokemonData)
    }
}

data class PokemonData(
    val name: String,
    val level: Int,
    val species: SpeciesShort
)
