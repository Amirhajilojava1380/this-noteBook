package ir.fod.thisnotebook.data.datasource

import ir.fod.thisnotebook.data.databaseroom.Dao
import ir.fod.thisnotebook.data.databaseroom.Entity.SplashEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(

    private val dao: Dao

) {

    suspend fun insertImage( splashEntity: SplashEntity ){

        dao.insertImage(splashEntity)

    }


    fun selectImage() : Flow<List<SplashEntity>> {

        return dao.selectImage()

    }

    suspend fun deleteImage(id : Int){

        return dao.deleteById(id)

    }

}