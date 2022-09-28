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


class MainActivity : Activity()
{
    var sm: SensorManager? = null
    var textView1: TextView? = null
    var textView2: TextView? = null
    lateinit var list: List<*>
    lateinit var list2: List<*>
    var sel: SensorEventListener = object : SensorEventListener
    {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values
            textView1!!.text = """
                x: ${values[0]}
                y: ${values[1]}
                z: ${values[2]}
                """.trimIndent()
        }

    }
    var sel2: SensorEventListener = object : SensorEventListener
    {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            val values = event.values
            textView2!!.text = """
                x: ${values[0]}
                y: ${values[1]}
                z: ${values[2]}
                """.trimIndent()
        }
    }
    public override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Get a SensorManager instance */sm = getSystemService(SENSOR_SERVICE) as SensorManager
        textView1 = findViewById<View>(R.id.textView1) as TextView
        textView2 = findViewById<View>(R.id.textView2) as TextView
        list = sm!!.getSensorList(Sensor.TYPE_ROTATION_VECTOR)
        if ((list as MutableList<Sensor>?)!!.size > 0) {
            sm!!.registerListener(sel, (list as MutableList<Sensor>?)!!.get(0) as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Toast.makeText(baseContext, "Error: No Rotation Vector", Toast.LENGTH_LONG).show()
        }


    }

    override fun onResume()
    {
        list2 = sm!!.getSensorList(Sensor.TYPE_GYROSCOPE)
           // if ((list2 as MutableList<Sensor>?)!!.size > 0)
            //{
                sm!!.registerListener(sel2, (list2 as MutableList<Sensor>?)!!.get(0) as Sensor, SensorManager.SENSOR_DELAY_NORMAL)
            //} else {
            //Toast.makeText(baseContext, "Error: No Accelerometer.", Toast.LENGTH_LONG).show()
        //}
            super.onResume()
    }
    override fun onStop()
    {

                sm!!.unregisterListener(sel)
                sm!!.unregisterListener(sel2)

            super.onStop()
    }
}
