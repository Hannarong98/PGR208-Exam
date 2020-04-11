package no.kristiania.foreignlands.ui.overviews

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_overview.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.dao.PlacesDaoSQL
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.ui.details.DetailActivity
import no.kristiania.foreignlands.ui.map.MapsActivity
import no.kristiania.foreignlands.ui.utils.ListClickListener

class OverviewActivity : AppCompatActivity(), ListClickListener {

    private lateinit var adapter: OverviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
        val api = NoForeignLandsApiService()
        val daoSQL = PlacesDaoSQL(this)
        val repository = OverviewRepository(api)
        val viewModel by viewModels<OverviewViewModel> { OverviewViewModelFactory(repository, daoSQL) }
        viewModel.fetchPlaces()
        viewModel.getPlaces().observe(this, Observer { places ->
            recycler_view_overviews.also {
                it.layoutManager = LinearLayoutManager(this)
                adapter = OverviewAdapter(places, this)
                it.adapter = adapter
            }
            adapter.onDetachedFromRecyclerView(recycler_view_overviews)
        }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)

    }


    override fun onNameClick(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("placeID", id)
        startActivity(intent)
    }

    override fun onIconClick(lat: Double, lon: Double) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("long", lon)
        startActivity(intent)
    }

}
