package com.example.roomview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomview.R
import com.example.roomview.models.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
     var mWords: List<Word> ?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (mWords==null)
            return 0
        else return mWords!!.size
    }

    override fun onBindViewHolder(holder: WordAdapter.ViewHolder, position: Int) {
        holder.bindTo(mWords!!.get(position))
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bindTo(word: Word) {
            with(itemView) {
                textView.text = word.mWord
            }
        }
    }


    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }
}