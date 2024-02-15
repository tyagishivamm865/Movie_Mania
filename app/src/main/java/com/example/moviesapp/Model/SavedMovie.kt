package com.example.moviesapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedMovies")
data class SavedMovie(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)
