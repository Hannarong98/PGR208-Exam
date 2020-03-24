package com.example.foreignlands

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foreignlands.data.TestData
import com.example.foreignlands.data.response.ApiResponse
import com.example.foreignlands.data.response.Feature
import kotlinx.android.synthetic.main.places_row.view.*

class PlaceAdapter(private val places: ApiResponse) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.places_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = places.features.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = places.features[position].properties.name
        holder.id.text = places.features[position].properties.id

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.place_name
        val id: TextView = itemView.place_id
    }
}
