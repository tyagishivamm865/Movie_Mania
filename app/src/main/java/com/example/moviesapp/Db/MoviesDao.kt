package com.example.moviesapp.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.Model.SavedMovie


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (movie:SavedMovie): Long

    @Query("SELECT * FROM SavedMovies")
    fun getAllSavedMovies(): LiveData<List<SavedMovie>>

    @Delete
    fun deleteArticle(movie: SavedMovie)
}