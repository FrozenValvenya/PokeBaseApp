package ru.frozenpriest.pokebase.data.remote

object HttpRoutes {
    private const val BASE_URL = "http://10.0.2.2:8080"
    private const val AUTH_URL = "$BASE_URL/auth"
    const val LOGIN_URL = "$AUTH_URL/login"
    const val REGISTER_URL = "$AUTH_URL/register"

    const val OWNED_POKEMON = "$BASE_URL/TODO"
}
