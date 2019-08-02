package com.example.rueda.s2next.registrar_pago

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.R
import kotlinx.android.synthetic.main.activity_registrar_pago.*

class Registrar_pago : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_pago)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Register payment"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
