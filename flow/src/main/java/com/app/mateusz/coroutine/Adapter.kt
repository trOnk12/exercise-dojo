package com.app.mateusz.coroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_item.view.*

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    val dataSet: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_item,
            parent,
            false
        )

        return ViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    inner class ViewHolder(private val textView: View) : RecyclerView.ViewHolder(textView){
        fun bind(value : String){
            textView.textView.text = value
        }
    }

}