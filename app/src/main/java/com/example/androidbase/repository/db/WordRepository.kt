package com.example.androidbase.repository.db

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.androidbase.repository.db.dao.WordDao
import com.example.androidbase.repository.db.tables.Word


internal class WordRepository(context: Context) {
    private val mWordDao: WordDao

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>>

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(word: Word?) {
        DataBase.databaseWriteExecutor.execute { mWordDao.insert(word) }
    }

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    init {
        val db: DataBase? = DataBase.getDatabase(context)
        mWordDao = db?.wordDao()!!
        allWords = mWordDao.alphabetizedWords
    }
}