package no.kristiania.foreignlands

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.places_row.view.*
import no.kristiania.foreignlands.data.response.ApiResponse

class OverviewAdapter(private val places: ApiResponse) : RecyclerView.Adapter<OverviewAdapter.ViewHolder>(), Filterable {


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

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}
