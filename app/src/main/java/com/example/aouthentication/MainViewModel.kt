package com.example.aouthentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {

    val bluetoothState = MutableStateFlow(false)
    val muteState = MutableStateFlow(false)
    val wifiState = MutableStateFlow(false)
    val compassState = MutableStateFlow(false)
    val batteryPercent = MutableStateFlow(0)
    val batteryPercentCorrect = MutableStateFlow(false)

    val enabled = combine(
        bluetoothState,
        muteState,
        wifiState,
        compassState,
        batteryPercentCorrect
    ) { bluetooth, mute, wifi, compass, battery ->
        battery && bluetooth && wifi && mute && compass
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)


}