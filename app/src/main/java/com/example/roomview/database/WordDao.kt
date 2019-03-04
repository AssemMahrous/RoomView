package com.example.roomview.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomview.models.Word

@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteWords()

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}