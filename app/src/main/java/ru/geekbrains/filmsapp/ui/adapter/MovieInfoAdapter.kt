package ru.geekbrains.filmsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.model.data.Movie


class MovieInfoAdapter(private var values: List<String>) : RecyclerView.Adapter<MovieInfoAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder =
        MViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_detail, parent, false))

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: MViewHolder, position: Int) = holder.bind(values[position])


    class MViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val movieDetail: TextView = view.findViewById(R.id.text_view_detail)

        fun bind(data: String) {
            movieDetail.text = data
        }

    }
}