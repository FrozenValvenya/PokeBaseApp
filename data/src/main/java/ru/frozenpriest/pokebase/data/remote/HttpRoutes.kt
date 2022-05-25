package ru.frozenpriest.pokebase.data.remote

object HttpRoutes {
    private const val BASE_URL = "http://10.0.2.2:8080"
    private const val AUTH_URL = "$BASE_URL/auth"
    const val LOGIN_URL = "$AUTH_URL/login"
    const val REGISTER_URL = "$AUTH_URL/register"

    const val OWNED_POKEMON = "$BASE_URL/pokemon"
    fun getPokemonDetailsRoute(pokemonId: String) = "$OWNED_POKEMON/$pokemonId"

    const val ADD_POKEMON = "$BASE_URL/pokemon"

    const val SPECIES_URL = "$BASE_URL/species"
    private const val MOVES_URL = "$SPECIES_URL/{speciesId}/moves/"
    fun getMovesRoute(speciesId: String) = MOVES_URL.replace("{speciesId}", speciesId)

    const val ADD_MOVE = "$OWNED_POKEMON/move"
}
