package com.example.androidbase.repository.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidbase.repository.db.dao.WordDao
import com.example.androidbase.repository.db.tables.Word


class WordRepository(context: Context) {
    private val mWordDatabase: Db
    private val mWordDao: WordDao

    var allWords: LiveData<List<Word>>

    init {
        mWordDatabase = Db.getDb(context, object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.e("###", "创建表成功")


            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.e("###", "打开成功")
            }
        })
        mWordDao = mWordDatabase.wordDao()
        allWords = mWordDao.alphabetizedWords
    }


    fun insert(word: Word?) {
        mWordDao.insert(word)
    }


}