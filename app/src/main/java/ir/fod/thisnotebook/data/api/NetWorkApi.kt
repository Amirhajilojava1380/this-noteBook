package ir.fod.thisnotebook.data.api

import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.Constant.Companion.URL_GET_NOTE
import retrofit2.Response
import retrofit2.http.GET

interface NetWorkApi {


    @GET(URL_GET_NOTE)
    suspend fun getNote() : Response<ResultListModel>



}