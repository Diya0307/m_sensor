package com.example.m_sesnor

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class MainActivity : Activity(),SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var textView1: TextView
    var list: List<*>? = null
   

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       
        setUpSensorStuff()
    }
    private fun setUpSensorStuff(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)?.also{
            sensorManager.registerListener(this,it,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_FASTEST)
        }
    }
    override fun onSensorChanged(event: SensorEvent) {
        val values = event.values
        """
                        x: ${values[0]}
                        y: ${values[1]}
                        z: ${values[2]}
                        """.trimIndent().also { textView1!!.text = it }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        if (list!!.size > 0) {
            sensorManager!!.unregisterListener(this)
        }
        super.onStop()

    }
}

