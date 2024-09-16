package ir.fod.thisnotebook.data.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.fod.thisnotebook.data.datasource.LocalDataSource
import ir.fod.thisnotebook.data.datasource.RemoteDataSource
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(

    localDataSource: LocalDataSource ,
    remoteDataSource: RemoteDataSource

){

    val local  = localDataSource
    val remote = remoteDataSource

}