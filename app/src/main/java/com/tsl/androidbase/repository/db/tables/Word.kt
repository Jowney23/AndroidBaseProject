package com.tsl.androidbase.repository.db.tables

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val  id :Int,

    @NonNull
    @ColumnInfo(name = "word") val word: String
)