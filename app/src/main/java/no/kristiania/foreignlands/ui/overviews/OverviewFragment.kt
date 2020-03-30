package no.kristiania.foreignlands.ui.overviews

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.overview_fragment.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.OverviewRepository

class OverviewFragment : Fragment(), View.OnClickListener {


    private lateinit var factory: OverviewViewModelFactory
    private lateinit var viewModel: OverviewViewModel
    private lateinit var adapter: OverviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = NoForeignLandsApiService()
        val repository = OverviewRepository(api)
        factory =
            OverviewViewModelFactory(
                repository
            )
        viewModel = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        viewModel.fetchPlaces()
        viewModel.placesLiveData.observe(viewLifecycleOwner, Observer { places ->
            recycler_view_overviews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                adapter =
                    OverviewAdapter(
                        places,
                        this
                    )
                it.adapter = adapter
            }
        }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
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

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(v: View?) {
        TODO()
    }

}