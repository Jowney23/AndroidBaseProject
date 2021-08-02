package com.tsl.androidbase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tsl.androidbase.repository.db.WordRepository
import com.tsl.androidbase.repository.db.tables.Word


class WordViewModel(app: Application) : AndroidViewModel(app) {

    private var mRepository: WordRepository = WordRepository(app.applicationContext)

    lateinit var mAllWords: LiveData<List<Word>>

    init {
       mAllWords = mRepository.allWords

    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word?) {
        mRepository.insert(word)
    }
}