package com.example.miappLibreria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class ListaLibrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_libros)
        val titulos = intent.getStringExtra("titulos")
        val autores = intent.getStringExtra("autores")
        val generos = intent.getStringExtra("generos")
        val descripciones = intent.getStringExtra("descripciones")

        val librosRecibidos = ArrayList<Libro>()

        if (!titulos.isNullOrEmpty()) {
            val listaTitulos = titulos.split("|||")
            val listaAutores = autores?.split("|||") ?: emptyList()
            val listaGeneros = generos?.split("|||") ?: emptyList()
            val listaDescripciones = descripciones?.split("|||") ?: emptyList()

            for (i in listaTitulos.indices) {
                val autor = listaAutores.getOrElse(i) { "" }
                val genero = listaGeneros.getOrElse(i) { "" }
                val descripcion = listaDescripciones.getOrElse(i) { "" }

                librosRecibidos.add(
                    Libro(listaTitulos[i], autor, genero, descripcion)
                )
            }
        }
        val recyclerLibros = findViewById<RecyclerView>(R.id.recyclerLibros)

        recyclerLibros.layoutManager = LinearLayoutManager(this)

        recyclerLibros.adapter = LibroAdapter(librosRecibidos)

        val botonVolver = findViewById<Button>(R.id.botonVolver)

        botonVolver.setOnClickListener {
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}