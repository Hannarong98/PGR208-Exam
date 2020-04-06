package no.kristiania.foreignlands.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.DetailsRepository

class DetailActivity : AppCompatActivity() {

    private var placeID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val api = NoForeignLandsApiService()
        val repository = DetailsRepository(api)
        placeID = intent.getStringExtra("placeID")

        val viewModel by viewModels<DetailViewModel> { DetailViewModelFactory(repository) }
        viewModel.fetchDetails(placeID!!)
        viewModel.placeDetailLiveData.observe(this, Observer { place ->

            var placeComment = place.comments.replace("(<[^>]*>)|(&[a-z]+;)".toRegex(), "")

            if (placeComment.isEmpty()) {
                placeComment = getString(R.string.place_no_description)
                detail_description.text = placeComment
            }

            detail_description.text = placeComment
            detail_name.text = place.name
            if (place.banner.isNotBlank()) {
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