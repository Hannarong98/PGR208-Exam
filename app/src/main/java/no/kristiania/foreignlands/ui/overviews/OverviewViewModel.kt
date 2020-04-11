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



    private val placesLiveData = MutableLiveData<MutableList<Places>>()
    fun getPlaces(): LiveData<MutableList<Places>> = placesLiveData

    fun fetchPlaces(){
        viewModelScope.launch {
            /*val places: MutableList<Places> = if(dao.fetchAll().isNotEmpty()){
                e("DB SIZE IF BLOCK", dao.fetchAll().size.toString())
                dao.fetchAll()
            } else {
                e("DB SIZE ELSE BLOCK", dao.fetchAll().size.toString())
                dao.addList(repository.getPlaces())
                repository.getPlaces()!!
            }*/
            val places = repository.getPlaces()
            placesLiveData.postValue(places)
        }
    }

}
