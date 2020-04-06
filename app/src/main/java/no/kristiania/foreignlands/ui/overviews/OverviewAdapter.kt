package no.kristiania.foreignlands.ui.overviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.places_row.view.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.model.overviews.Places
import no.kristiania.foreignlands.ui.utils.ListClickListener
import java.util.*

class OverviewAdapter(private var places: MutableList<Places>, var onClickListener: ListClickListener) : RecyclerView.Adapter<OverviewAdapter.ViewHolder>(), Filterable {

   private var searchList: MutableList<Places> = places



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.places_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = places.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(places[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindViewHolder(item: Places){
            itemView.place_name.text = item.properties.name
            itemView.place_id.text = item.properties.id
            itemView.tag = item

            itemView.layout_row.setOnClickListener {
                onClickListener.onNameClick(item.properties.id)
            }

            itemView.pin_button.setOnClickListener {
                onClickListener.onIconClick(item.geometry.coordinates[0], item.geometry.coordinates[1])
            }

        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val listTemp = searchList
                val filterResults = FilterResults()
                val resultList: MutableList<Places> = ArrayList()
                if(charSequence.isEmpty()) {
                    resultList.addAll(listTemp)
                } else {

                    val searchString = charSequence.toString().trim()

                    listTemp.filter {
                        it.properties.name.contains(searchString, ignoreCase = true)
                    }.forEach {
                        resultList.add(it)
                    }
                }

                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(c: CharSequence, result: FilterResults) {
                val newList: MutableList<Places>? = result.values as MutableList<Places>
                if(!newList.isNullOrEmpty()) {
                    places = newList
                    notifyDataSetChanged()
                }
            }


        }
    }
}
