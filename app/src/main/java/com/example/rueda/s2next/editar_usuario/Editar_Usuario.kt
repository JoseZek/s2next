package com.example.rueda.s2next.editar_usuario

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.calendario.Calendario
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.inicio.Inicio
import kotlinx.android.synthetic.main.activity_editar__usuario.*
import kotlinx.android.synthetic.main.activity_editar__usuario.et_fecha
import kotlinx.android.synthetic.main.content_alta__usuario.btn_feha_nacimiento

class Editar_Usuario : AppCompatActivity() {

    var nombre_edit: String = "Editando a "
    var dato_id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar__usuario)
        val actionbar = supportActionBar
        var titulo: String = "Editar"
        actionbar!!.title = titulo
        actionbar.setDisplayHomeAsUpEnabled(true)
        btn_feha_nacimiento.setOnClickListener {
            Calendario(this, et_fecha)
        }
        val id_put = intent.extras
        dato_id = id_put.getInt("id_put")
        TraerDatos()
        actionbar!!.title = nombre_edit
        editar.setOnClickListener { save() }
    }

    fun TraerDatos() {

        var db = DataBaseHandler(this)
        var data = db.editar_usuario(dato_id)

        if (data.size == 0) {
            Toast.makeText(this, "Debes seleccionar por lo menos a un usuario", Toast.LENGTH_LONG).show()
        } else {
            for (i in 0..(data.size - 1)) {
                et_nombre.setText(data.get(i).name)
                nombre_edit += data.get(i).name + " "
                et_segundo_nombre.setText(data.get(i).middle_name)
                et_ap_p.setText(data.get(i).last_name)
                nombre_edit += data.get(i).last_name
                et_ap_m.setText(data.get(i).second_name)
                et_fecha.setText(data.get(i).birthdate)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(applicationContext, Inicio::class.java)
        startActivity(intent)
        finish()
        return true
    }


    fun save() {

        if (!et_nombre.text.isEmpty() && !et_ap_p.text.isEmpty()
            && !et_ap_m.text.isEmpty() && !et_fecha.text.isEmpty()
        ) {
            var db = DataBaseHandler(this)
            db.update_usuario(
                dato_id, et_nombre.text.toString(), et_segundo_nombre.text.toString(),
                et_ap_p.text.toString(), et_ap_m.text.toString(), et_fecha.text.toString()
            )
            val intent = Intent(applicationContext, Inicio::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Ups! algun campo esta vacio, verificalo!", Toast.LENGTH_SHORT).show()
        }

    }
}
