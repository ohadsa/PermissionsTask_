package com.example.aouthentication

import android.bluetooth.BluetoothManager
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import com.example.aouthentication.ui.components.AppUI

class MainActivity : ComponentActivity(), SensorEventListener {

    private val viewModel: MainViewModel by viewModels()
    private var wifiManager: WifiManager? = null
    private var audioManager: AudioManager? = null
    private var bluetoothManager: BluetoothManager? = null

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                AppUI()
            }
        }
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        bluetoothManager =
            applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            event.values?.let {
                viewModel.compassState.value = (it[1] < 1.5) && (it[1] > -1.5)
            }
        }

    viewModel.bluetoothState.value = bluetoothManager?.adapter?.isEnabled ?: false
    wifiManager?.isWifiEnabled?.let{
        viewModel.wifiState.value = it
    }
    audioManager?.let{
        viewModel.muteState.value =
            it.ringerMode == AudioManager.RINGER_MODE_SILENT || it.ringerMode == AudioManager.RINGER_MODE_VIBRATE
    }
}

override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit


}




