package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.repository.DetailsRepository


class DetailViewModel(private val repository: DetailsRepository) : ViewModel() {

    val placeDetailLiveData = MutableLiveData<PlaceDetail>()
    fun fetchDetails(id: String) {
        viewModelScope.launch {
            val detail = repository.getRemotePlaceDetails(id)
            placeDetailLiveData.postValue(detail)
        }
    }
}
