package com.tecnm.tepic.ladm_u4_practica2_canvasysensores

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.View
import androidx.core.content.ContextCompat.getSystemService


class Lienzo(act:MainActivity): View(act) {
    var coorX = 200f
    val principal = act
    var globoMov = globoMov(this)
    val cielo = BitmapFactory.decodeResource(principal.resources, R.drawable.cielo)
    val cielo_oscuro = BitmapFactory.decodeResource(principal.resources, R.drawable.cielo_oscuro)
    val globo = BitmapFactory.decodeResource(principal.resources, R.drawable.globo)
    var proximidad = 0

    init{
        globoMov.start()
    }

    override fun onDraw(c: Canvas){
        super.onDraw(c)
        val p = Paint()

        if(proximidad < 5) {
            c.drawBitmap(cielo_oscuro, 0f, 0f, p)
            c.drawBitmap(cielo_oscuro, 0f, 500f, p)
        }else{
            c.drawBitmap(cielo, 0f, 0f, p)
            c.drawBitmap(cielo, 0f, 500f, p)
        }
        c.drawBitmap(globo, coorX,150f,p)
    }
}

class globoMov(p:Lienzo):Thread(){
    val puntero = p

    override  fun run(){
        super.run()
        while(true){
            puntero.principal.runOnUiThread{
                puntero.invalidate()
            }
            sleep(20)
        }
    }
}