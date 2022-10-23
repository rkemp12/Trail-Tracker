package com.rkemp12.trailtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.rkemp12.trailtracker.R

class TrailListAdapter(
    private val context: Context,
    private val dataset: List<Trail>
    ) : RecyclerView.Adapter<TrailListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val trailName: TextView = view.findViewById(R.id.trailName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.trail_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.trailName.text = context.resources.getString(item.stringResourceId)
    }
    override fun getItemCount() = dataset.size
}