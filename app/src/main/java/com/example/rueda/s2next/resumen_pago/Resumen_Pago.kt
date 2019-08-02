package com.example.rueda.s2next.resumen_pago

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.R
import kotlinx.android.synthetic.main.activity_resumen__pago.*

class Resumen_Pago : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen__pago)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Resum payment"
        actionbar.setDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
