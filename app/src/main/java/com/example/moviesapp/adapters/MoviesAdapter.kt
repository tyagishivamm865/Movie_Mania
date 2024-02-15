package com.example.moviesapp.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.Model.Search
import com.example.moviesapp.R
import com.example.moviesapp.Ui.Detail

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
lateinit var movie:Search
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgview = itemView.findViewById<ImageView>(R.id.imgMovie)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvYear = itemView.findViewById<TextView>(R.id.tvYear)
        val tvType = itemView.findViewById<TextView>(R.id.tvtype)

        init {
            itemView.setOnClickListener{
                var intent = Intent(itemView.context, Detail::class.java)
                intent.putExtra("imdb",movie.imdbID)
                itemView.context.startActivity(intent)
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.Poster == newItem.Poster
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
           movie = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(movie.Poster).into(holder.imgview)
            holder.tvTitle.text = movie.Title
            holder.tvYear.text = movie.Year
            holder.tvType.text = movie.Type
        }

    }

}







