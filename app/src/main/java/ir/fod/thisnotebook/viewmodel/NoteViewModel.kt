package ir.fod.thisnotebook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.fod.thisnotebook.data.repository.Repository
import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.NetWorkCheck
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(

    private val repository: Repository

):ViewModel() {

    val dataNote : MutableLiveData<NetWorkCheck<ResultListModel>> = MutableLiveData()

    fun getNote () = viewModelScope.launch {

        dataNote.value = NetWorkCheck.Lod()


        val dataList = repository.remote.getNote()

        dataNote.value = checkNote(dataList)






    }

    private fun checkNote(dataList: Response<ResultListModel>): NetWorkCheck<ResultListModel>? {

        when{

            dataList.message().toString().contains("timeout")->{

               return   NetWorkCheck.Error("timeout")

            }

            dataList.code() == 402 ->{

                return   NetWorkCheck.Error("Error : 402")

            }


            dataList.body()?.result.isNullOrEmpty()->{

                return  NetWorkCheck.Error("yes null")

            }


            dataList.isSuccessful->{

                return   NetWorkCheck.Success(dataList.body())

            }

            else -> {

                return  NetWorkCheck.Error(dataList.message())

            }

        }

    }


}