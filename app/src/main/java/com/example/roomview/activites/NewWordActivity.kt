package com.example.roomview.activites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.roomview.R
import kotlinx.android.synthetic.main.activity_new_word.*


class NewWordActivity : AppCompatActivity() {

    val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word!!.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()

        }
    }
}