package no.kristiania.foreignlands.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.DetailsRepository


class DetailFragment : Fragment() {

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

            var placeComment = place.comments.replace("(<[^>]*>)|(&[a-z]+;)".toRegex(),"")

            if (placeComment.isEmpty()) {
                placeComment = getString(R.string.place_no_description)
                detail_description.text = placeComment
            }

            detail_description.text = placeComment
            detail_name.text = place.name
            if(place.banner.isNotBlank()){
                Glide.with(this)
                    .load(place.banner)
                    .into(detail_image)
            } else {
                Glide.with(this)
                    .load(R.drawable.img_placeholder)
                    .into(detail_image)
            }
        })

    }

}
