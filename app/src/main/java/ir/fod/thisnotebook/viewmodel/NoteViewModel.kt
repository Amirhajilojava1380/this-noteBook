package ir.fod.thisnotebook .viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.fod.thisnotebook.data.repository.Repository
import ir.fod.thisnotebook.model.Message
import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.NetWorkCheck
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(

    private val repository: Repository

):ViewModel() {

    // get note
    val dataNote: MutableLiveData<NetWorkCheck<ResultListModel>> = MutableLiveData()

    fun getNote() = viewModelScope.launch {

        dataNote.value = NetWorkCheck.Lod()

        val dataList = repository.remote.getNote()

        dataNote.value = checkNote(dataList)

    }

    private fun checkNote(dataList: Response<ResultListModel>): NetWorkCheck<ResultListModel> {

        when {

            dataList.message().toString().contains("timeout") -> {

                return NetWorkCheck.Error("timeout")

            }

            dataList.code() == 402 -> {

                return NetWorkCheck.Error("Error : 402")

            }


            dataList.body()?.result.isNullOrEmpty() -> {

                return NetWorkCheck.Error("yes null")

            }


            dataList.isSuccessful -> {

                return NetWorkCheck.Success(dataList.body())

            }

            else -> {

                return NetWorkCheck.Error(dataList.message())

            }

        }

    }

    //send note

    val sendNoteStatus: MutableLiveData<NetWorkCheck<Message>> = MutableLiveData()

    fun sendNoteAsHashMap(title: String) = viewModelScope.launch {

        val data = HashMap<String, String>()

        data["title"] = title

        sendNoteStatus.value = NetWorkCheck.Lod()

        try {

            val response = repository.remote.sentNote(data)

            if (response.isSuccessful && response.body()?.status == true) {

               Log.d("Response", "Response: ${response.body()?.message}")
               sendNoteStatus.value = NetWorkCheck.Message(response.body()?.message)


            } else {


                sendNoteStatus.value = NetWorkCheck.Error(response.body()?.message)

            }

        } catch (e: Exception) {

            Log.e("Exception", e.message ?: "Unknown Error")
            sendNoteStatus.value = NetWorkCheck.Error(e.message ?: "Unknown Error")

        }

    }



}
