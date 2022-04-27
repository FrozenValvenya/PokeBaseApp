package ru.frozenpriest.pokebase.presentation

sealed class NavigationDestination(val destination: String) {
    object PokemonDetails : NavigationDestination("pokemon_details")
}
