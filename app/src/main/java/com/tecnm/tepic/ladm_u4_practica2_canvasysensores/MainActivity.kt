package com.tecnm.tepic.ladm_u4_practica2_canvasysensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager : SensorManager
    lateinit var lienzo :Lienzo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lienzo = Lienzo(this)
        setContentView(lienzo)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
            SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when(event.sensor.type) {
            Sensor.TYPE_PROXIMITY -> {
                var dist = event.values[0]
                lienzo.proximidad = dist.toInt()
            }

            Sensor.TYPE_ACCELEROMETER -> {
                var x = event.values[0]
                lienzo.coorX = (x*70)+400
            }
        }
        lienzo.postInvalidate()

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}