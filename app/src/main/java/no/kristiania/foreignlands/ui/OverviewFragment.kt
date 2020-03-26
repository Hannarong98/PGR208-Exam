package no.kristiania.foreignlands.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.overview_fragment.*
import no.kristiania.foreignlands.OverviewAdapter
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.OverviewRepository

class OverviewFragment : Fragment() {

    private lateinit var factory: OverviewViewModelFactory
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = NoForeignLandsApiService()
        val repository = OverviewRepository(api)
        factory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(OverviewViewModel::class.java)
        viewModel.getOverviews()
        viewModel.overviews.observe(viewLifecycleOwner, Observer { places ->
            recycler_view_overviews.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = OverviewAdapter(places)
            }
        })
    }

}