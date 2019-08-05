package com.example.rueda.s2next.resumen_pago

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.R
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.inicio.Inicio
import kotlinx.android.synthetic.main.activity_resumen__pago.*
import kotlinx.android.synthetic.main.content_resumen__pago.*
import java.util.*

class Resumen_Pago : AppCompatActivity() {

    var fecha: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen__pago)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.title = "Resum payment"
        actionbar.setDisplayHomeAsUpEnabled(true)
        var db = DataBaseHandler(this)

        btn_calendario.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            tv_fecha.setText("")
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                fecha = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth
                tv_fecha.setText(fecha)
                resum_pay(db)
            }, year, month, day)
            dpd.show()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(applicationContext, Inicio::class.java)
        startActivity(intent)
        finish()
        return true
    }

    fun resum_pay(db: DataBaseHandler) {
        var data = db.resum_pay(fecha)
        if (data.size == 0) {
            tv_resumen_datos.text = "No hay pagos para este dia :) \n intenta con otra fecha"
        } else {
            tv_resumen_datos.text = ""
            for (i in 0..(data.size - 1)) {
                tv_resumen_datos.append(
                    data.get(i).name + " " + data.get(i).middle_name + " " + data.get(i).last_name + " " + data.get(i).second_name +
                            "\n Pagos: " + data.get(i).num_pago + "\nTotal: " + data.get(i).pago + "\n\n\n "
                )
            }
        }
    }

}
