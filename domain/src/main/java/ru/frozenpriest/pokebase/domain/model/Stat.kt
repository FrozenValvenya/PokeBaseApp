package ru.frozenpriest.pokebase.domain.model

import androidx.annotation.StringRes
import ru.frozenpriest.pokebase.domain.R

data class Stat(
    @StringRes val name: Int,
    val value: Int
) {
    companion object {
        fun makeHP(value: Int) = Stat(R.string.hp, value)
        fun makeAttack(value: Int) = Stat(R.string.attack, value)
        fun makeDefence(value: Int) = Stat(R.string.defence, value)
        fun makeSpAttack(value: Int) = Stat(R.string.sp_attack, value)
        fun makeSpDefence(value: Int) = Stat(R.string.sp_defence, value)
        fun makeSpeed(value: Int) = Stat(R.string.speed, value)
    }
}
