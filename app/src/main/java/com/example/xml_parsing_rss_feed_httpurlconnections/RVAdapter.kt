package com.example.xml_parsing_rss_feed_httpurlconnections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xml_parsing_rss_feed_httpurlconnections.model.item
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter (var itemArray: MutableList<item>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = itemArray[position]
        holder.itemView.apply{
            tv.text = item.description.toString()
        }
    }

    override fun getItemCount()= itemArray.size
}