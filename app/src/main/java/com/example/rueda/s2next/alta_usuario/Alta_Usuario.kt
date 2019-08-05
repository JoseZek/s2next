package com.example.rueda.s2next.alta_usuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.calendario.Calendario
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.db.user
import com.example.rueda.s2next.inicio.Inicio
import kotlinx.android.synthetic.main.activity_alta__usuario.*
import kotlinx.android.synthetic.main.content_alta__usuario.*

class Alta_Usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta__usuario)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Create User"
        actionbar.setDisplayHomeAsUpEnabled(true)
        genero.isChecked = true
        btn_feha_nacimiento.setOnClickListener {
            Calendario(this, et_fecha)
        }
        btn_save.setOnClickListener { btn_save() }
        genero.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                genero.text = "Masculino"
            } else {
                genero.text = "Femenino"
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(applicationContext, Inicio::class.java)
        startActivity(intent)
        finish()
        return true
    }

    fun btn_save() {

        if (!nombre.text.isEmpty() && !ap_p.text.isEmpty()
            && !ap_m.text.isEmpty() && !et_fecha.text.isEmpty()
        ) {

            var new_user = user(
                nombre.text.toString(), segundo_nombre.text.toString(), ap_p.text.toString(), ap_m.text.toString(),
                et_fecha.text.toString(), genero()
            )

            Toast.makeText(this, new_user.toString(), Toast.LENGTH_LONG).show()

            var db = DataBaseHandler(this)
            db.insertCustomer(new_user)

            nombre.setText("")
            segundo_nombre.setText("")
            ap_p.setText("")
            ap_m.setText("")
            et_fecha.setText("")
            genero.isChecked = true

        } else {
            Toast.makeText(this, "Ups! algun campo esta vacio, verificalo!", Toast.LENGTH_SHORT).show()
        }

    }

    fun genero() = if (genero.isChecked) 1 else 2

}
