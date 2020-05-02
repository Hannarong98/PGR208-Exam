package no.kristiania.foreignlands.ui.overviews


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import no.kristiania.foreignlands.data.repository.OverviewRepository


class OverviewViewModel(private val repository: OverviewRepository) : ViewModel() {
    val placesList = liveData {
        Log.i("Overview VM", "Requesting data from repository")
        emit(repository.getPlaces())
    }
}

