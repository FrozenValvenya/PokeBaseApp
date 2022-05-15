package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.frozenpriest.pokebase.domain.model.Type

@Composable
fun TypeChip(type: Type) {
    Card(
        backgroundColor = type.getColor(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = type.typeName,
            color = Color.White,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
        )
    }
}
