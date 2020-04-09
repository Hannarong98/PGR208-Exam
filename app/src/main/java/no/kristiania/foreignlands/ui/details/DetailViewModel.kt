package no.kristiania.foreignlands.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import no.kristiania.foreignlands.data.db.model.details.PlaceDetail
import no.kristiania.foreignlands.data.repository.DetailsRepository


class DetailViewModel(private val repository: DetailsRepository) : ViewModel() {

    private val placeDetailLiveData = MutableLiveData<PlaceDetail>()
    fun getDetail(): LiveData<PlaceDetail> = placeDetailLiveData
    fun fetchDetails(id: String) {
        viewModelScope.launch {
            val detail = repository.getRemotePlaceDetails(id)
            placeDetailLiveData.postValue(detail)
        }
    }
}
