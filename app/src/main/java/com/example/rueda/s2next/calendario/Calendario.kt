package com.example.rueda.s2next.calendario

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.content_registrar_pago.view.*
import java.util.*

class Calendario {

    constructor(context: Context, v: View) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            v.et_fecha.setText("" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth)
        }, year, month, day)
        dpd.show()
    }

}