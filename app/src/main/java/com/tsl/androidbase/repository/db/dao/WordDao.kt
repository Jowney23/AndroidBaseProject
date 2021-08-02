package com.tsl.androidbase.repository.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsl.androidbase.repository.db.tables.Word


@Dao
interface WordDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word?)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @get:Query("SELECT * FROM word_table ORDER BY word ASC")
    val alphabetizedWords: LiveData<List<Word>>
}