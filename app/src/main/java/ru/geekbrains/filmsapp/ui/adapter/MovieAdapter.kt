package ru.geekbrains.filmsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.model.data.Movie
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

class MovieAdapter(private val context: Context, private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    var values: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_view_movie, parent, false))

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(values[position])
}

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.image_view_poster)
    private val title: TextView = view.findViewById(R.id.text_view_title)
    private val genres: TextView = view.findViewById(R.id.text_view_genres)
    private val releaseDate: TextView = view.findViewById(R.id.text_view_release_date)
    private val popularity: TextView = view.findViewById(R.id.text_view_popularity)

    fun bind(movie: Movie) {
        // TODO: load poster by poster path
        title.text = movie.title
        // TODO:join genres
        releaseDate.text = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(movie.releaseDate)
        popularity.text = String.format(view.resources.getString(R.string.popularity), movie.popularity);
    }

}