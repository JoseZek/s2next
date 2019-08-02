package com.example.rueda.s2next.editar_usuario

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.R

class Editar_Usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar__usuario)
        val actionbar = supportActionBar
        actionbar!!.title = "Edit User"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
