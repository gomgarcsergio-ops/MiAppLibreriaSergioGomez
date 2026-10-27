package com.example.miappLibreria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {
    val libros = ArrayList<Libro>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val generos = arrayOf(
            "Novela",
            "Fantasía",
            "Misterio",
            "Ciencia ficción",
            "Romance",
            "Terror"
        )

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            generos
        )

        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adaptador

        val campoTitulo = findViewById<EditText>(R.id.editTextText)
        val campoDescripcion = findViewById<EditText>(R.id.editTextDescripcion)
        val botonGuardar = findViewById<Button>(R.id.button)

        botonGuardar.setOnClickListener {
            val titulo = campoTitulo.text.toString().trim()

            /*Con estas condiciones logro que en el caso de que no se ponga ni el nombre ni el autor
            * del libro se muestren los mensajes dentro de los if, si se cumple pues se ejecutan los else
            * y se guarda todo correctamente*/
            if (titulo.isEmpty()) {
                Toast.makeText(
                    this,
                    "Escribe el nombre del libro",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val campoAutor = findViewById<EditText>(R.id.editTextText2)
                val autor = campoAutor.text.toString().trim()

                if (autor.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Escribe el autor del libro",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val genero = spinner.selectedItem.toString()
                    val descripcion = campoDescripcion.text.toString().trim()

                    //Guardamos el libro con sus cuatro datos
                    libros.add(Libro(titulo, autor, genero, descripcion))

                    Toast.makeText(
                        this,
                        "Libro guardado: $titulo",
                        Toast.LENGTH_SHORT
                    ).show()

                    campoTitulo.text.clear()
                    campoAutor.text.clear()
                    campoDescripcion.text.clear()
                }
            }

        }

        val botonVerLibros = findViewById<Button>(R.id.button2)

        botonVerLibros.setOnClickListener {
            val titulos = libros.joinToString("|||") { it.titulo }
            val autores = libros.joinToString("|||") { it.autor }
            val generos = libros.joinToString("|||") { it.genero }
            val descripciones = libros.joinToString("|||") { it.descripcion }

            val intent = Intent(this, ListaLibrosActivity::class.java)
            intent.putExtra("titulos", titulos)
            intent.putExtra("autores", autores)
            intent.putExtra("generos", generos)
            intent.putExtra("descripciones", descripciones)

            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}