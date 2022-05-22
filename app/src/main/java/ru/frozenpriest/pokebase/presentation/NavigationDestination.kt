package ru.frozenpriest.pokebase.presentation

sealed class NavigationDestination(val destination: String) {
    object LoginRegister : NavigationDestination("login_or_register")
    object PokemonDetails : NavigationDestination("pokemon_details/{id}")
    object OwnedPokemons : NavigationDestination("owned_pokemon")
    object NewPokemon : NavigationDestination("new_pokemon")
    object PokemonBattle : NavigationDestination("pokemon_battle/{id1}/{id2}")
}

fun String.withId(id: String): String {
    return this.replace("{id}", id)
}

fun String.withTwoPokemon(id1: String, id2: String): String {
    return this.replace("{id1}", id1).replace("{id2}", id2)
}
