package com.example.rueda.s2next

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                        val intent = Intent(applicationContext,Inicio::class.java)
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
            val intent = Intent(this,Inicio::class.java)
            startActivity(intent)
            firstLayout = true
        }
    }
}
