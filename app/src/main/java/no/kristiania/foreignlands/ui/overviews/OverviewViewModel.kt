package no.kristiania.foreignlands.ui.overviews

import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.db.model.overviews.Places


class OverviewViewModel(private val repository: OverviewRepository) : ViewModel() {

    private val placesLiveData = MutableLiveData<List<Places>>()
    fun getPlaces(): LiveData<List<Places>> = placesLiveData
    fun fetchPlaces(){
        e("OVM", "fetchPlaces")
        viewModelScope.launch {
            val places = repository.getPlaces()
            placesLiveData.postValue(places)
        }
    }

}
