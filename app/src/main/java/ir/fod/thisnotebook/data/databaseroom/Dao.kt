package ir.fod.thisnotebook.data.databaseroom


import androidx.room.*
import androidx.room.Dao
import ir.fod.thisnotebook.data.databaseroom.Entity.SplashEntity
import ir.fod.thisnotebook.utils.Constant.Companion.IMAGE_QUERY_DELETE
import ir.fod.thisnotebook.utils.Constant.Companion.IMAGE_QUERY_SELECT
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    ////Splash

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage ( splashEntity: SplashEntity )

    @Query(IMAGE_QUERY_SELECT)
    fun selectImage() : Flow<List<SplashEntity>>

    @Query(IMAGE_QUERY_DELETE)
    suspend fun deleteById(id :Int)


    ////


}