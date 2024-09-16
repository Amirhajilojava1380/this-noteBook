package ir.fod.thisnotebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.fod.thisnotebook.data.databaseroom.Entity.SplashEntity
import ir.fod.thisnotebook.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(


    private val repository: Repository

) : ViewModel() {

    val dataLocal : LiveData<List<SplashEntity>> = repository.local.selectImage().asLiveData()

    fun insertImage( splashEntity: SplashEntity ) = viewModelScope.launch {

        repository.local.insertImage(splashEntity)

    }

    fun deleteImage( id : Int ) = viewModelScope.launch {

        repository.local.deleteImage(id)

    }



}