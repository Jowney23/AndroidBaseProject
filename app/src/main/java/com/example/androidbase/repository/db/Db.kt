package com.example.androidbase.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidbase.repository.db.dao.WordDao
import com.example.androidbase.repository.db.tables.Word



@Database(entities = [Word::class], version = 1)
abstract class Db : RoomDatabase() {
    //添加Dao
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: Db
        fun getDb(context: Context, callback: Callback): Db {
            if (INSTANCE == null) {
                synchronized(Db::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            //名字不要加_下划线（callback导致无法正常回调）
                            Db::class.java, "CheeseDatabases"
                        ).addCallback(callback)
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}