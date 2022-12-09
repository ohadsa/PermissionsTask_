package com.example.aouthentication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aouthentication.R
import com.example.aouthentication.ui.components.utils.CostumeButton
import com.example.aouthentication.ui.components.utils.DrawableImage

@Composable
fun SecondPage(onBackTapped: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            DrawableImage(
                modifier = Modifier.size(128.dp).align(Alignment.Center),
                id = R.drawable.star,
                svg = true
            )
        }
        Spacer(modifier = Modifier.height(80.dp))

        CostumeButton(
            modifier = Modifier.padding(horizontal = 80.dp),
            enabled = true,
            text = stringResource(id = R.string.back)
        ) {
            onBackTapped()
        }


    }
}

@Preview
@Composable
fun PreviewSecondPage(){
    SecondPage {

    }
}
