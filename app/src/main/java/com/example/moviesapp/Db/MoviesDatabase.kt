package com.example.moviesapp.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.Model.MoviesData
import com.example.moviesapp.Model.SavedMovie


@Database(entities = [SavedMovie::class], version = 1)
abstract class MoviesDatabase():RoomDatabase() {

    abstract fun movieDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        fun getDatabase(context: Context): MoviesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MoviesDatabase::class.java,
                        "quoteDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}