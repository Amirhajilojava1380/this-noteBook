package ir.fod.thisnotebook.data.databaseroom

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.fod.thisnotebook.data.databaseroom.Entity.SplashEntity

@Database(entities = [SplashEntity::class] , version = 1 , exportSchema = false )
abstract class DataBase  :RoomDatabase() {

    abstract fun dao() : Dao

}