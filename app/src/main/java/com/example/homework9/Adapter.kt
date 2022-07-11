package com.example.homework9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val items: ArrayList<UserData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var mListener: OnItemsClickListener

    interface OnItemsClickListener {
        fun onDeleteClicked(position: Int)
        fun onUpdateClicked(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemsClickListener) {
        mListener = listener
    }

    class ViewHolder(view: View, listener: OnItemsClickListener) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val lastName: TextView = view.findViewById(R.id.last_name)
        val email: TextView = view.findViewById(R.id.email)

        init {
            view.findViewById<Button>(R.id.delete_btn).setOnClickListener {
                listener.onDeleteClicked(adapterPosition)
            }
            view.findViewById<Button>(R.id.update_btn).setOnClickListener {
                listener.onUpdateClicked(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view, mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.lastName.text = items[position].lastName
        holder.email.text = items[position].email
    }

    override fun getItemCount(): Int {
        return items.size
    }
}