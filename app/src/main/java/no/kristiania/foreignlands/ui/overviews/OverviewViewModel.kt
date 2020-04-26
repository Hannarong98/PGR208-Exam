package no.kristiania.foreignlands.ui.overviews


import android.util.Log.i
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.db.model.overviews.Places


class OverviewViewModel(private val repository: OverviewRepository) : ViewModel() {

    // Encapsulated because its mutable
    private val placesLiveData = MutableLiveData<List<Places>>()

    // Should only be called once
    // Even if configuration changes happens
    init {
        i("Overview VM", "Requesting data from repository")
        viewModelScope.launch {
            val places = repository.getPlaces()
            placesLiveData.postValue(places)
        }
    }

    val placesList: LiveData<List<Places>>
    get() = placesLiveData

}
