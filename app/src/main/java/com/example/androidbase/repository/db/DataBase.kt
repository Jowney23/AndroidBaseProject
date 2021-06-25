package com.example.androidbase.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidbase.repository.db.dao.WordDao
import com.example.androidbase.repository.db.tables.Word
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao?

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DataBase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}