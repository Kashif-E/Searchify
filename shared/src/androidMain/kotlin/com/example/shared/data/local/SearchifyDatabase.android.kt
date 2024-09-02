package com.example.shared.data.local

import android.content.Context
import androidx.room.Room

actual fun provideDatabase(context: Any): MovieDB {
  return  Room
        .databaseBuilder(context as Context, MovieDB::class.java,"SearchifyMovie.db")
        .build()
}