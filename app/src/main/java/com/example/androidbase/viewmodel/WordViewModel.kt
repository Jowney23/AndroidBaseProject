package com.example.androidbase.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidbase.APP
import com.example.androidbase.repository.db.WordRepository
import com.example.androidbase.repository.db.tables.Word


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