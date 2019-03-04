package com.example.roomview.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomview.repos.WordRepository
import com.example.roomview.models.Word


class WordViewModel(application: Application) : AndroidViewModel(Application()) {
    var repository: WordRepository = WordRepository(application)
    private var mWords: LiveData<List<Word>> = repository.getAllWords()


    fun getAllWords(): LiveData<List<Word>> {
        return mWords
    }

    fun insert(word: Word) {
        repository.insertWord(word)
    }
}