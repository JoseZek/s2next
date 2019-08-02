package com.example.rueda.s2next.alta_usuario

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rueda.s2next.R
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


        btn_feha_nacimiento.setOnClickListener {
            calendar()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
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

}
