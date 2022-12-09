package com.example.aouthentication.ui.components

import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aouthentication.MainViewModel
import com.example.aouthentication.R
import com.example.aouthentication.ui.components.utils.CostumeButton
import com.example.aouthentication.ui.components.utils.CostumeTextField
import com.example.aouthentication.ui.components.utils.DrawableImage
import com.example.aouthentication.ui.components.utils.Term

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstPage(
    onContinueTapped: () -> Unit,
) {

    val batteryManager = LocalContext.current.getSystemService(BATTERY_SERVICE) as BatteryManager


    val viewModel: MainViewModel = viewModel()

    val bluetoothState by viewModel.bluetoothState.collectAsState()
    val muteState by viewModel.muteState.collectAsState()
    val wifiState by viewModel.wifiState.collectAsState()
    val compassState by viewModel.compassState.collectAsState()
    val batteryPercent by viewModel.batteryPercent.collectAsState()
    val batteryPercentCorrect by viewModel.batteryPercentCorrect.collectAsState()
    val enabled by viewModel.enabled.collectAsState()

    val allTerms = listOf(
        Term(wifiState, R.drawable.red_wifi, R.drawable.green_wifi),
        Term(muteState, R.drawable.red_mute, R.drawable.green_mute),
        Term(bluetoothState, R.drawable.red_bluetooth, R.drawable.green_bluetooth),
        Term(compassState, R.drawable.red_orientation, R.drawable.green_orientation),
    )




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Row {
            allTerms.forEachIndexed { index, item ->
                if (index != 0) Spacer(modifier = Modifier.width(16.dp))
                DrawableImage(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    id = if (item.valid) item.green else item.red,
                    svg = true
                )

            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Box(Modifier.fillMaxWidth()) {

            CostumeTextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 40.dp)
                    .wrapContentWidth()
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min),
                value = if (batteryPercent == 0) "" else batteryPercent.toString(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    textColor = Color.DarkGray,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    cursorColor = Color.DarkGray,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.emptyStateTextField),
                        maxLines = 1,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W500,
                    )
                },
                onValueChange = {

                    if (it == "") {
                        viewModel.batteryPercent.value = 0

                    } else {
                        it.toIntOrNull()?.let { num ->
                            viewModel.batteryPercent.value = num
                            val batLevel: Int =
                                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
                            viewModel.batteryPercentCorrect.value = (batLevel == num)
                        }
                    }
                },
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        DrawableImage(
            modifier = Modifier.size(48.dp),
            id = if (batteryPercentCorrect) R.drawable.safe else R.drawable.safe_gray,
            svg = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        CostumeButton(
            modifier = Modifier.padding(horizontal = 80.dp),
            enabled = enabled,
            text = stringResource(id = R.string.enter)
        ) {
            onContinueTapped()
        }

    }


}


@Preview
@Composable
fun PreviewFirstPage() {
    FirstPage {
    }
}