package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.detail_fragment.*

import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.DetailsRepository
import no.kristiania.foreignlands.data.repository.OverviewRepository

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = NoForeignLandsApiService()
        val repository = DetailsRepository(api)
        val factory = DetailViewModelFactory(repository)
        val id = DetailFragmentArgs.fromBundle(arguments!!).placeID

        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        viewModel.fetchDetails(id)
        viewModel.placeDetailLiveData.observe(viewLifecycleOwner, Observer { place ->
            place_detail_desc.text = place.comments
            place_detail_name.text = place.name
        })

    }

}
