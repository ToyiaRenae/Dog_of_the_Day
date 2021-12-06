package com.example.dogoftheday.ViewModels

import androidx.lifecycle.*
import com.example.dogoftheday.database.DogImageDao
import com.example.dogoftheday.database.DogImageEntity
import com.example.dogoftheday.network.DogPhoto
import com.example.dogoftheday.network.DogPhotoApi
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val dogImageDao: DogImageDao) : ViewModel() {

    private val _currentlyDisplayedImage = MutableLiveData<DogPhoto>()
    val currentlyDisplayedImage: LiveData<DogPhoto> = _currentlyDisplayedImage

    init {
        getNewDog()
    }
    fun getNewDog(){
        viewModelScope.launch {
            _currentlyDisplayedImage.value = DogPhotoApi.retrofitService.getRandomPhoto()
        }
    }

    fun addDog(dogImageEntity: DogImageEntity)
    {
        viewModelScope.launch {
            dogImageDao.addDogImage(dogImageEntity)
        }
    }
    fun deleteMostRecentDog(){
        viewModelScope.launch {
            dogImageDao.deleteDog()
        }
    }
    fun getAllDogs(): LiveData<List<DogImageEntity>> {
        return dogImageDao.getAllDogImages().asLiveData()
    }
}
class MainViewModelFactory(
    private val dogImageDao: DogImageDao) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dogImageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }