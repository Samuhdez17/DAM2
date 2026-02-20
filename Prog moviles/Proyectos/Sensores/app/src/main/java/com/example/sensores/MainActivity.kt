package com.example.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sensores.ui.theme.SensoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SensorScreen(context = LocalContext.current)
                }
            }
        }
    }
}

@Composable
private fun SensorScreen(
    context: Context
) {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    var x by remember { mutableStateOf(0f) }
    var y by remember { mutableStateOf(0f) }
    var z by remember { mutableStateOf(0f) }
    var luzValor by remember { mutableStateOf(0f) }
    val listener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                when (it.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        x = it.values[0]
                        y = it.values[1]
                        z = it.values[2]
                    }
                    Sensor.TYPE_LIGHT -> {
                        luzValor = it.values[0]
                    }
                }
            }
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }
    DisposableEffect(Unit) {
        sensorManager.registerListener(listener, acelerometro, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(listener, luz, SensorManager.SENSOR_DELAY_NORMAL)
        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Acelerómetro", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("X: $x")
        Text("Y: $y")
        Text("Z: $z")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sensor de Luz", style = MaterialTheme.typography.titleMedium)
        Text("Luz: $luzValor lux")
    }
}