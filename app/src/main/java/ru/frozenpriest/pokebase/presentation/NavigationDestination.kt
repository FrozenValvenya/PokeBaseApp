package ru.frozenpriest.pokebase.presentation

sealed class NavigationDestination(val destination: String) {
    object PokemonDetails : NavigationDestination("pokemon_details/{id}")
    object OwnedPokemons : NavigationDestination("owned_pokemon")
}

fun String.withId(id: String): String {
    return this.replace("{id}", id)
}
