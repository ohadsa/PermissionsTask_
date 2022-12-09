package com.example.aouthentication.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt

@Composable
fun CostumeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onEnter: () -> Unit = {},
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .graphicsLayer {
                    if (enabled) {
                        scaleX = if (isPressed) 0.98f else 1.0f
                        scaleY = if (isPressed) 0.98f else 1.0f
                    }
                }
                .clip(RoundedCornerShape(4.dp))
                .let {
                    if (enabled) it.clickableNoFeedback(interactionSource) {
                        onEnter()
                    } else it
                }
                .border(
                    1.dp,
                    color = if (enabled) Color("#FFC043".toColorInt()) else Color.Gray,
                    RoundedCornerShape(4.dp)
                )
                .background(if (enabled) Color("#FFF9EC".toColorInt()) else Color.Transparent ,RoundedCornerShape(4.dp) )
            ,
        ) {


            Text(
                text = text,
                lineHeight = 22.sp,
                color = if (enabled) Color.Black else Color.Gray,
                modifier = Modifier.background(Color.Transparent)
            )

        }

    }
}

@Composable
@Preview
fun PreviewFollowButton() {
    CostumeButton(enabled = false, text = "enter")
}