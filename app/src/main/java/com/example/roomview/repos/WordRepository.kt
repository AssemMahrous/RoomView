package com.example.roomview.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomview.database.WordDao
import com.example.roomview.database.WordRoomDatabase
import com.example.roomview.models.Word

import android.os.AsyncTask


class WordRepository(application: Application) {
    var mWords: LiveData<List<Word>>
    var wordDoa: WordDao

    init {
        val db = WordRoomDatabase.getInstance(application)
        wordDoa = db!!.wordDao()
        mWords = wordDoa.getAllWords()
    }


    fun getAllWords(): LiveData<List<Word>> {
        return mWords
    }

    fun insertWord(word: Word) {
        insertAsyncTask(wordDoa).execute(word);
    }


    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) :
        AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}