package no.kristiania.foreignlands.ui.overviews

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.View
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
    private var lastClicked = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        progress_indicator.visibility = View.VISIBLE

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = NoForeignLandsApiService(networkConnectionInterceptor)
        val dao = MyDatabase.invoke(this).placesDao()
        val repository = OverviewRepository(api, dao)

        val viewModel by viewModels<OverviewViewModel> { OverviewViewModelFactory(repository) }
        viewModel.placesList.observe(this, Observer { places ->
            recyclerview_overviews.also {
                adapter = OverviewAdapter(places, this)
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this)
                invalidateOptionsMenu()
                progress_indicator.visibility = View.INVISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        item.isEnabled = false
        //Adapter has to be initialized inside of observer before search can happen
        //Otherwise its going to crash the application thus, isEnabled is set to false
        if (this@OverviewActivity::adapter.isInitialized)
            item.isEnabled = true

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

    override fun onIconClick(lat: Double, lon: Double, placeName: String) {
        if (delay()) return
        val intent = Intent(this, MapsActivity::class.java)
        intent.apply {
            putExtra("placeName", placeName)
            putExtra("lat", lat)
            putExtra("long", lon)
        }
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
