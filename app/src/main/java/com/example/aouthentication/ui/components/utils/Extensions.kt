package com.example.aouthentication.ui.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableNoFeedback(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
): Modifier = clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = onClick
)


fun Modifier.clickableNoFeedback(onClick: () -> Unit) = composed {
    clickable(interactionSource = remember {
        MutableInteractionSource()
    }, indication = null, onClick = onClick)
}

