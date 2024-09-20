package ir.fod.thisnotebook.data.api

import ir.fod.thisnotebook.model.Message
import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.Constant.Companion.URL_GET_NOTE
import ir.fod.thisnotebook.utils.Constant.Companion.URL_SEND_NOTE
import retrofit2.Response
import retrofit2.http.*

interface NetWorkApi {


    @GET(URL_GET_NOTE)
    suspend fun getNote() : Response<ResultListModel>

    @FormUrlEncoded
    @POST(URL_SEND_NOTE)
    suspend fun sentNote( @FieldMap() title :HashMap<String , String> ) : Response<Message>




}