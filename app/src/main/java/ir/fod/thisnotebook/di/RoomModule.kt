package ir.fod.thisnotebook.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.fod.thisnotebook.data.databaseroom.DataBase
import ir.fod.thisnotebook.utils.Constant.Companion.DATABASE_NAME
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): DataBase{

       return Room.databaseBuilder(

            context ,
            DataBase::class.java,
            DATABASE_NAME

       ).build()

    }


    @Singleton
    @Provides
    fun  providesDao(dataBase: DataBase) = dataBase.dao()

}