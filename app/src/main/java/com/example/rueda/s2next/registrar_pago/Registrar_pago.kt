package com.example.rueda.s2next.registrar_pago

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.calendario.Calendario
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.inicio.Inicio
import com.example.rueda.s2next.pago.pago
import kotlinx.android.synthetic.main.activity_registrar_pago.*
import kotlinx.android.synthetic.main.content_registrar_pago.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Registrar_pago : AppCompatActivity() {

    var dato_id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_pago)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Registar Pago"
        actionbar.setDisplayHomeAsUpEnabled(true)
        val id_put = intent.extras
        dato_id = id_put.getInt("id_put")
        btn_calendario.setOnClickListener { Calendario(this, et_fecha) }
        btn_pago.setOnClickListener { pago() }
        TraerDatos()
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(applicationContext, Inicio::class.java)
        startActivity(intent)
        finish()
        return true
    }

    fun TraerDatos() {

        var db = DataBaseHandler(this)
        var data = db.editar_usuario(dato_id)

        if (data.size == 0) {
            Toast.makeText(this, "Debes seleccionar por lo menos a un usuario", Toast.LENGTH_LONG).show()
        } else {
            for (i in 0..(data.size - 1)) {
                tv_nombre.append(
                    data.get(i).name + " " + data.get(i).middle_name + " " +
                            data.get(i).last_name + " " + data.get(i).second_name
                )
            }
        }
    }

    fun pago() {
        if (!et_cantidad.text.isEmpty() && !et_fecha.text.isEmpty()) {

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)

            Log.i("Fecha completa", formatted)
            var new_pay =
                pago(dato_id, formatted.toString(), et_fecha.text.toString(), et_cantidad.text.toString().toInt())
            var db = DataBaseHandler(this)
            db.insertPay(new_pay)
            et_cantidad.setText("")
            et_fecha.setText("")

        } else {
            Toast.makeText(this, "Ups! algun campo esta vacio, verificalo!", Toast.LENGTH_SHORT).show()
        }
    }

}
