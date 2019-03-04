package com.example.roomview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomview.models.Word
import android.os.AsyncTask
import android.os.AsyncTask.execute
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.annotation.NonNull


@Database(entities = [Word::class], exportSchema = false, version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java, "word.db"
                    )
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }


    private class PopulateDbAsync internal constructor(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private val mDao: WordDao

        init {
            mDao = db.wordDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteWords()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            return null
        }
    }

}