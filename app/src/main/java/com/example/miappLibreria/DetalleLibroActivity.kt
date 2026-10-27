package com.example.miappLibreria

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleLibroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_libro)

        // Se guardan los datos que llegan desde la lista
        val titulo = intent.getStringExtra("titulo")
        val autor = intent.getStringExtra("autor")
        val genero = intent.getStringExtra("genero")
        val descripcion = intent.getStringExtra("descripcion")

        // Aquí se buscan los textos del diseño:
        val textoTitulo = findViewById<TextView>(R.id.textoDetalleTitulo)
        val textoAutor = findViewById<TextView>(R.id.textoDetalleAutor)
        val textoGenero = findViewById<TextView>(R.id.textoDetalleGenero)
        val textoDescripcion = findViewById<TextView>(R.id.textoDetalleDescripcion)

        // Las siguientes lineas muestran los datos recibidos
        textoTitulo.text = titulo ?: "Sin título"
        textoAutor.text = "Autor: ${autor ?: "Desconocido"}"
        textoGenero.text = "Género: ${genero ?: "Sin género"}"
        textoDescripcion.text = "Descripción: ${descripcion ?: "Sin descripción"}"

        /*En esta ultima linea he creado la variable botonVolver para que se pueda volver a la ventana
        anterior, es decir a la de lista*/
        val botonVolver = findViewById<Button>(R.id.botonVolverDetalle)

        botonVolver.setOnClickListener {
            finish()
        }
    }
}