package no.kristiania.foreignlands.ui.overviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.kristiania.foreignlands.data.repository.OverviewRepository
import no.kristiania.foreignlands.data.db.model.overviews.Places


class OverviewViewModel(private val repository: OverviewRepository) : ViewModel() {

    private val placesLiveData = MutableLiveData<MutableList<Places>>()
    fun getPlaces(): LiveData<MutableList<Places>> = placesLiveData

    fun fetchPlaces(){
        viewModelScope.launch {
            val places = repository.getRemotePlaces()
            placesLiveData.postValue(places)
        }
    }

}
