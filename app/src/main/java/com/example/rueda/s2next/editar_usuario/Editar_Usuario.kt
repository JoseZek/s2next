package com.example.rueda.s2next.editar_usuario

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.inicio.Inicio
import kotlinx.android.synthetic.main.activity_editar__usuario.*
import kotlinx.android.synthetic.main.content_alta__usuario.btn_feha_nacimiento
import java.util.*

class Editar_Usuario : AppCompatActivity() {

    var nombre_edit: String = "Editando a "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar__usuario)
        val actionbar = supportActionBar
        var titulo: String = "Editar"
        actionbar!!.title = titulo
        actionbar.setDisplayHomeAsUpEnabled(true)
        btn_feha_nacimiento.setOnClickListener {
            calendar()
        }
        editar.setOnClickListener { save() }

        val id_put = intent.extras
        val dato_id = id_put.getInt("id_put")
        Log.i("Intento", dato_id.toString())
        TraerDatos(dato_id)
        actionbar!!.title = nombre_edit

    }

    fun TraerDatos(id: Int){

        var db = DataBaseHandler(this)
        var data = db.editar_usuario(id)

        if(data.size == 0){
            Toast.makeText(this,"Debes seleccionar por lo menos a un usuario",Toast.LENGTH_LONG).show()
        }else{
            for(i in 0..(data.size - 1)){
                et_nombre.setText(data.get(i).name)
                nombre_edit += data.get(i).name + " "
                et_segundo_nombre.setText(data.get(i).middle_name)
                et_ap_p.setText(data.get(i).last_name)
                nombre_edit += data.get(i).last_name
                et_ap_m.setText(data.get(i).second_name)
                et_fecha_nacimiento.setText(data.get(i).birthdate)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(applicationContext, Inicio::class.java)
        startActivity(intent)
        finish()
        return true
    }


    fun calendar(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            et_fecha_nacimiento.setText("" + year + "/" + (monthOfYear+1) + "/" + dayOfMonth )
        }, year, month, day)

        dpd.show()
    }
    fun save(){
        /*Ejecutar query update y salir del layout si  correcto*/
    }
}
