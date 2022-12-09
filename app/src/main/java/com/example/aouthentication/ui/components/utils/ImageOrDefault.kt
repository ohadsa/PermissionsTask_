package com.example.aouthentication.ui.components.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState
import androidx.compose.foundation.Image
@Composable
fun ImageOrDefault(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    @DrawableRes defaultValue: Int? = null,
    loader: @Composable (BoxScope.(imageState: GlideImageState.Loading) -> Unit)? = null,
) {
    ImageOrDefault(
        imageUrl = imageUrl, width = null, height = null, modifier = modifier,
        defaultValue = defaultValue,
        loader = loader
    )
}

@Composable
fun ImageOrDefault(
    imageUrl: String?,
    width: Dp?,
    height: Dp?,
    modifier: Modifier = Modifier,
    @DrawableRes defaultValue: Int? = null,
    loader: @Composable (BoxScope.(imageState: GlideImageState.Loading) -> Unit)? = null,
) {
    if (LocalInspectionMode.current) {
        if (defaultValue != null) {
            Image(
                painter = painterResource(id = defaultValue),
                contentDescription = "",
                modifier = modifier
            )
        } else {
            Box(
                modifier = modifier
                    .background(Color.Red)
            )
        }
    } else if (!imageUrl.isNullOrEmpty())
        GlideImage(
            imageModel = { imageUrl },
            modifier = modifier, loading = loader,
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            requestOptions = {
                LocalDensity.current.run {
                    val w = width ?: 100.dp
                    val h = height ?: 100.dp
                    RequestOptions().override(
                        w.toPx().toInt(), h.toPx().toInt()
                    )
                }
            }
        )
    else if (defaultValue != null)
        DrawableImage(
            id = defaultValue,
            contentScale = ContentScale.Crop,
            modifier = modifier,
            svg = false,
        )
}