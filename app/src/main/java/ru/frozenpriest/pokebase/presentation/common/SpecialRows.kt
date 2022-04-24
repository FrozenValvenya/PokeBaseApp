package ru.frozenpriest.pokebase.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.presentation.theme.StatBackground
import ru.frozenpriest.pokebase.presentation.theme.StatBad
import ru.frozenpriest.pokebase.presentation.theme.StatGood

@Composable
fun TextRow(name: String, value: String) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(2f),
            color = MaterialTheme.colors.secondaryVariant,
            text = name
        )
        Text(modifier = Modifier.weight(3f), color = MaterialTheme.colors.secondary, text = value)
    }
}

@Composable
fun IntStatRow(stat: Stat, limit: Int) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(2f),
            color = MaterialTheme.colors.secondaryVariant,
            text = stringResource(id = stat.name)
        )
        Text(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colors.secondary,
            text = stat.value.toString()
        )
        StatIndicator(modifier = Modifier.weight(3f), value = stat.value, limit = limit)
    }
}

@Composable
fun StatIndicator(modifier: Modifier, value: Int, limit: Int) {
    val percent = (value / limit.toFloat()).coerceAtMost(1f)
    Canvas(modifier = modifier) {
        drawLine(
            color = StatBackground,
            start = Offset(0f, size.height / 2f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = 10f,
            cap = StrokeCap.Round,
        )
        val color = if (percent >= 0.5f) StatGood else StatBad
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2f),
            end = Offset(percent * size.width, size.height / 2f),
            strokeWidth = 10f,
            cap = StrokeCap.Round,
        )
    }
}
