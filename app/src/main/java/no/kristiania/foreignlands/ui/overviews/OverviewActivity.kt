package no.kristiania.foreignlands.ui.overviews

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_overview.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.db.MyDatabase
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.utils.NetworkConnectionInterceptor
import no.kristiania.foreignlands.ui.details.DetailActivity
import no.kristiania.foreignlands.ui.map.MapsActivity
import no.kristiania.foreignlands.ui.utils.ListClickListener

class OverviewActivity : AppCompatActivity(), ListClickListener {

    private lateinit var adapter: OverviewAdapter
    private var lastClicked = 0L;


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)


        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = NoForeignLandsApiService(networkConnectionInterceptor)
        val dao = MyDatabase.invoke(this).placesDao()
        val repository = OverviewRepository(api, dao)

        val viewModel by viewModels<OverviewViewModel> { OverviewViewModelFactory(repository) }
        viewModel.fetchPlaces()
        viewModel.getPlaces().observe(this, Observer { places ->
            recyclerview_overviews.also {
                it.layoutManager = LinearLayoutManager(this)
                adapter = OverviewAdapter(places, this)
                it.adapter = adapter
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
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
        if (delay()) return
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("placeID", id)
        startActivity(intent)
    }

    override fun onIconClick(lat: Double, lon: Double) {
        if (delay()) return
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("long", lon)
        startActivity(intent)
    }

    private fun delay(): Boolean {
        if (SystemClock.elapsedRealtime() - lastClicked < 2000) {
            return true
        }
        lastClicked = SystemClock.elapsedRealtime()
        return false
    }

}
