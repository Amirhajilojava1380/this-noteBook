package ir.fod.thisnotebook.data.datasource

import ir.fod.thisnotebook.data.api.NetWorkApi
import ir.fod.thisnotebook.model.ListModel
import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.NetWorkCheck
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(

    private val api: NetWorkApi

) {

    suspend fun getNote() : Response<ResultListModel>{

        return api.getNote()

    }




}