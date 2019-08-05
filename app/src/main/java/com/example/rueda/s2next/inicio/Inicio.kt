package com.example.rueda.s2next.inicio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rueda.s2next.R
import com.example.rueda.s2next.alta_usuario.Alta_Usuario
import com.example.rueda.s2next.db.DataBaseHandler
import com.example.rueda.s2next.editar_usuario.Editar_Usuario
import com.example.rueda.s2next.registrar_pago.Registrar_pago
import com.example.rueda.s2next.resumen_pago.Resumen_Pago
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.content_inicio.*

class Inicio : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {


    var id_btn_uno: Int = 1
    var id_btn_dos: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar!!.title = "S2NEXT"
        actionbar.setDisplayHomeAsUpEnabled(true)
        var db = DataBaseHandler(this)
        create_user(db)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.Alta -> {
            val intent = Intent(applicationContext,Alta_Usuario::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun color_fondo(i: Int):Int{
        if(i%2 == 0){
            return Color.parseColor("#FCFCFC")
        }else{
            return Color.parseColor("#247db8")
        }
    }
    fun color_fuente(i: Int):Int{
        if(i%2 == 0){
            return Color.parseColor("#247db8")
        }else{
            return Color.parseColor("#ffffff")
        }
    }
    override fun onLongClick(p0: View?): Boolean {
        //Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext,Editar_Usuario::class.java)
        intent.putExtra("id_put",p0?.id)
        startActivity(intent)
        finish()
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onClick(p0: View?) {

        val id_buton: Int = p0?.id!!
        if(id_buton%2  == 0){
           // Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, Registrar_pago::class.java)
            startActivity(intent)
        }else if(id_buton%2  == 1){
            //Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, Resumen_Pago::class.java)
            startActivity(intent)
        }

        if(id_buton == -1){
            val intent = Intent(applicationContext, Alta_Usuario::class.java)
            startActivity(intent)
        }

        finish()
    }
    fun create_user (db:DataBaseHandler){
        var data = db.readData()

        if(data.size == 0){

            val tv_dynamic = TextView(this)
            tv_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            tv_dynamic.gravity = Gravity.CENTER
            tv_dynamic.textSize = 40f
            tv_dynamic.setOnLongClickListener(this)
            tv_dynamic.text = "No Cuentas con registros pulsa el boton para agregar uno nuevo"

            //tv_dynamic.layoutParams.apply { (this as LinearLayout.LayoutParams).gravity = 1 }

            // add TextView to LinearLayout
            contenedor.addView(tv_dynamic)
            //Se crean los botones 2
            val btn2_dynamic = Button(this)
            btn2_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            btn2_dynamic.textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
            btn2_dynamic.gravity = Gravity.CENTER
            btn2_dynamic.textSize = 20f
            btn2_dynamic.text = "Registrar"
            btn2_dynamic.setOnClickListener(this)

            //btn2_dynamic.setTextColor(color_fuente(i))
            val idm = -1
            btn2_dynamic.id = idm

            // add TextView to LinearLayout
            contenedor.addView(btn2_dynamic)

        }else {

            for (i in 0..(data.size - 1)) {

                Log.i("si entra", i.toString())

                val linear_layout = LinearLayout(this)
                linear_layout.orientation = LinearLayout.HORIZONTAL
                linear_layout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                linear_layout.gravity = Gravity.CENTER
                linear_layout.setBackgroundColor(color_fondo(i))
                linear_layout.layoutParams.apply { (this as LinearLayout.LayoutParams).setMargins(7, 10, 7, 5) }
                contenedor.addView(linear_layout)
                /*---------------------------------------------------------------------------------------------------------------------*/


                val tv_dynamic = TextView(this)
                tv_dynamic.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                tv_dynamic.gravity = Gravity.CENTER
                tv_dynamic.textSize = 15f
                tv_dynamic.append(data.get(i).name + " "  +data.get(i).middle_name + " " + data.get(i).last_name + " " + data.get(i).second_name)
                tv_dynamic.id = data.get(i).id!!
                tv_dynamic.setOnLongClickListener(this)
                tv_dynamic.setTextColor(color_fuente(i))
                tv_dynamic.layoutParams.apply { (this as LinearLayout.LayoutParams).weight = 1f }
                //tv_dynamic.layoutParams.apply { (this as LinearLayout.LayoutParams).gravity = 1 }

                // add TextView to LinearLayout
                linear_layout.addView(tv_dynamic)

                /*---------------------------------------------------------------------------------------------------------------------*/
                //Se crean los botones
                val btn1_dynamic = Button(this)
                btn1_dynamic.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                btn1_dynamic.textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                btn1_dynamic.textSize = 10f
                btn1_dynamic.text = "Resumen Pago"
                btn1_dynamic.setOnClickListener(this)
                // btn1_dynamic.setTextColor(color_fuente(i))
                btn1_dynamic.id = id_btn_uno
                id_btn_uno += 2
                btn1_dynamic.layoutParams.apply { (this as LinearLayout.LayoutParams).weight = 2f }

                // add TextView to LinearLayout
                linear_layout.addView(btn1_dynamic)

                //Se crean los botones 2
                val btn2_dynamic = Button(this)
                btn2_dynamic.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                btn2_dynamic.textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                btn2_dynamic.textSize = 8f
                btn2_dynamic.text = "Registrar pago"
                btn2_dynamic.setOnClickListener(this)

                //btn2_dynamic.setTextColor(color_fuente(i))
                btn2_dynamic.id = id_btn_dos
                id_btn_dos += 2
                btn2_dynamic.layoutParams.apply { (this as LinearLayout.LayoutParams).weight = 2f }

                // add TextView to LinearLayout
                linear_layout.addView(btn2_dynamic)

            }
        }
    }
}
