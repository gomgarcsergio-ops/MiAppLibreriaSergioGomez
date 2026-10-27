package com.example.miappLibreria

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter(
    private val libros: List<Libro>
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    class LibroViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val textoTitulo: TextView = itemView.findViewById(R.id.textoTitulo)
        val textoAutor: TextView = itemView.findViewById(R.id.textoAutor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro, parent, false)

        return LibroViewHolder(vista)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]

        // Con las siguientes dos lineas se muestran el título y el autor
        holder.textoTitulo.text = libro.titulo
        holder.textoAutor.text = libro.autor

        /*Con las siguientes lineas hago que al pulsar el libro dentro de lista se muestren los
        *detalles de este es decir que se ejecuta el activity DetalleLibro que he creado para ver
        * más inormación del libro que se seleccione en ese momento*/
        holder.itemView.setOnClickListener {
            val intent = Intent(
                holder.itemView.context,
                DetalleLibroActivity::class.java
            )

            // Y aquí utilizo los intent para enviar los datos del libro seleccionado
            intent.putExtra("titulo", libro.titulo)
            intent.putExtra("autor", libro.autor)
            intent.putExtra("genero", libro.genero)
            intent.putExtra("descripcion", libro.descripcion)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return libros.size
    }
}