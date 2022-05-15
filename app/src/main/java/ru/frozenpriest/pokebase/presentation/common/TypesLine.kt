package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ru.frozenpriest.pokebase.domain.model.Type

@Composable
fun TypesLine(types: List<Type>) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        items(types) { type ->
            TypeChip(type)
        }
    }
}
