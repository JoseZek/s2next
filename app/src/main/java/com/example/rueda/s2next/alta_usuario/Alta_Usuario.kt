package com.example.rueda.s2next.alta_usuario

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.db.user
import com.example.rueda.s2next.inicio.Inicio
import kotlinx.android.synthetic.main.activity_alta__usuario.*
import kotlinx.android.synthetic.main.content_alta__usuario.*
import java.util.*

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
            calendar()
        }
        btn_save.setOnClickListener { btn_save() }
        genero.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked){
                genero.text = "Masculino"
            }else{
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
    fun calendar(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            fecha_nacimiento.setText("" + year + "/" + (monthOfYear+1) + "/" + dayOfMonth )
        }, year, month, day)

        dpd.show()
    }
    fun btn_save(){

        if(!nombre.text.isEmpty() && !ap_p.text.isEmpty()
            && !ap_m.text.isEmpty() && !fecha_nacimiento.text.isEmpty()){

            var new_user = user(nombre.text.toString(),segundo_nombre.text.toString(),ap_p.text.toString(),ap_m.text.toString(),
                fecha_nacimiento.text.toString(),genero())

            Toast.makeText(this,new_user.toString(),Toast.LENGTH_LONG).show()

            var db = DataBaseHandler(this)
            db.insertCustomer(new_user)

            nombre.setText("")
            segundo_nombre.setText("")
            ap_p.setText("")
            ap_m.setText("")
            fecha_nacimiento.setText("")
            genero.isChecked = true



        }else{
            Toast.makeText(this,"Ups! algun campo esta vacio, verificalo!",Toast.LENGTH_SHORT).show()
        }

    }
    fun genero() = if (genero.isChecked) 1 else 2

}
