package ir.fod.thisnotebook.data.databaseroom.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.fod.thisnotebook.utils.Constant.Companion.IMAGE_COLUMNINFO
import ir.fod.thisnotebook.utils.Constant.Companion.TABEL_NAME_IMAGE

@Entity(tableName = TABEL_NAME_IMAGE)
class SplashEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = IMAGE_COLUMNINFO)
    var image: String


)



