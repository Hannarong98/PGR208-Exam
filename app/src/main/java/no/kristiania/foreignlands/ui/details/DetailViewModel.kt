package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.model.details.Place
import no.kristiania.foreignlands.data.repository.DetailsRepository
import kotlin.coroutines.CoroutineContext


class DetailViewModel(private val repository: DetailsRepository) : ViewModel() {

    val placeDetailLiveData = MutableLiveData<Place>()

    fun fetchDetails(id: String) {
        viewModelScope.launch {
            val detail = repository.getPlaceDetails(id)
            placeDetailLiveData.postValue(detail)
        }
    }
}
