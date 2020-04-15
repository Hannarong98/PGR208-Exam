package no.kristiania.foreignlands.ui.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import no.kristiania.foreignlands.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var lon: Double? = null
    private var placeName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        lon = intent.getDoubleExtra("long", -34.0)
        lat = intent.getDoubleExtra("lat", -34.0)
        placeName = intent.getStringExtra("placeName")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val placeCord = lat?.let { lon?.let { it1 -> LatLng(it1, it) } }
        mMap.addMarker(placeCord?.let {
            MarkerOptions()
                .position(it)
                .title(placeName)
                .snippet("${placeCord.latitude}, ${placeCord.latitude}")
        }).showInfoWindow()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(placeCord, 14f), 3000, null)
    }


}
