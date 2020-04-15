package no.kristiania.foreignlands.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import no.kristiania.foreignlands.R
import no.kristiania.foreignlands.data.api.NoForeignLandsApiService
import no.kristiania.foreignlands.data.repository.DetailsRepository
import no.kristiania.foreignlands.data.utils.NetworkConnectionInterceptor
import no.kristiania.foreignlands.ui.map.MapsActivity

class DetailActivity : AppCompatActivity() {

    private var placeID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        placeID = intent.getStringExtra("placeID")
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = NoForeignLandsApiService(networkConnectionInterceptor)
        val repository = DetailsRepository(api)

        val viewModel by viewModels<DetailViewModel> {
            DetailViewModelFactory(
                repository,
                placeID!!
            )
        }
        viewModel.detail.observe(this, Observer { place ->

            var placeComment = place.comments.replace("(<[^>]*>)|(&[a-z]+;)".toRegex(), "")

            if (placeComment.isEmpty()) {
                placeComment = getString(R.string.place_no_description)
                detail_description.text = placeComment
            } else {
                detail_description.text =
                    HtmlCompat.fromHtml(place.comments, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }

            detail_name.text = place.name

            detail_pin_button.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("lat", place.lat)
                intent.putExtra("long", place.lon)
                intent.putExtra("placeName", place.name)
                startActivity(intent)
            }

            Glide.with(this)
                .load(place.banner)
                .placeholder(R.drawable.img_placeholder)
                .into(detail_image)
        })

    }


}
