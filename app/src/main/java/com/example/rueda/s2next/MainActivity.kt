package com.example.rueda.s2next

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.alta_usuario.Alta_Usuario
import com.example.rueda.s2next.inicio.Inicio


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var firstLayout: Boolean = true
        if(firstLayout){
            val splash = object : Thread(){
                override fun run(){
                    try {
                        Thread.sleep(3000)
                        val intent = Intent(applicationContext, Inicio::class.java)
                        startActivity(intent)
                        finish()
                    }catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            splash.start()
           firstLayout = false

        }else{
            //Agregar if para saber si manda a alta si es que no hay registros o al Inicio
            val intent = Intent(this, Alta_Usuario::class.java)
            startActivity(intent)
        }
    }

}
