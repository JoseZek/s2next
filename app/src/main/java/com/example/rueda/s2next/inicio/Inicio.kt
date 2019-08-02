package com.example.rueda.s2next.inicio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.rueda.s2next.R
import com.example.rueda.s2next.alta_usuario.Alta_Usuario
import com.example.rueda.s2next.editar_usuario.Editar_Usuario
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.content_inicio.*

class Inicio : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar!!.title = "S2NEXT"
        actionbar.setDisplayHomeAsUpEnabled(true)


        var id_btn_uno: Int = 1
        var id_btn_dos: Int = 2
        for(i in 1..20) {

            val linear_layout = LinearLayout(this)
            linear_layout.orientation = LinearLayout.HORIZONTAL
            linear_layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linear_layout.gravity = Gravity.CENTER
            linear_layout.setBackgroundColor(color_fondo(i))
            linear_layout.layoutParams.apply { (this as LinearLayout.LayoutParams).setMargins(7,10,7,5) }
            linear_layout.id = i //Tendra el id del usuario
            contenedor.addView(linear_layout)
            /*---------------------------------------------------------------------------------------------------------------------*/

            //Se crea el contenido
            //Comenzamos por el nombre
            val tv_dynamic = TextView(this)
            tv_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            tv_dynamic.gravity = Gravity.CENTER
            tv_dynamic.textSize = 15f
            tv_dynamic.text = nombre(i)
            tv_dynamic.setOnLongClickListener(this)
            tv_dynamic.setTextColor(color_fuente(i))
            tv_dynamic.id = i
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
        R.id.Editar -> {
            val intent = Intent(applicationContext,Editar_Usuario::class.java)
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
    fun nombre(i: Int):String{
        if(i%2 == 0){
            return "Jose Alberto Ramirez Arista"
        }else{
            return "Vanessa Elizabeth Rodriguez Guerrero"
        }
    }
    /**override fun onClick(p0: View?) {
       if(p0?.id == 3){
           Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
           val intent = Intent(applicationContext, Registrar_pago::class.java)
           startActivity(intent)
       }else if(p0?.id == 2){
           Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
           val intent = Intent(applicationContext, Resumen_Pago::class.java)
           startActivity(intent)
       }
    }**/
    override fun onLongClick(p0: View?): Boolean {
        Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext,Editar_Usuario::class.java)
        startActivity(intent)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    override fun onClick(p0: View?) {
        val id_buton: Int = p0?.id!!
        if(id_buton%2  == 0){
            Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
            //val intent = Intent(applicationContext, Registrar_pago::class.java)
            //startActivity(intent)
        }else {
            Toast.makeText(this,"El id es ${p0?.id.toString()}",Toast.LENGTH_LONG).show()
            //val intent = Intent(applicationContext, Resumen_Pago::class.java)
            //startActivity(intent)
        }
    }

}
