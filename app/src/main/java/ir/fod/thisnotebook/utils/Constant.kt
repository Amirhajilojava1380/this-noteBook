package ir.fod.thisnotebook.utils

class Constant {

    companion object{

        ////room

        const val DATABASE_NAME = "NOTEBOOK"

        const val TABEL_NAME_IMAGE = "splash"

        const val IMAGE_COLUMNINFO = "image"

        //sql Query

        const val IMAGE_QUERY_SELECT = "SELECT * From $TABEL_NAME_IMAGE"

        const val IMAGE_QUERY_DELETE = "DELETE FROM $TABEL_NAME_IMAGE WHERE id = :id"

        ////url Retrofit

        const val URL = "http://192.168.1.5/note/"

        //api

        const val URL_GET_NOTE = "GetList.php"




    }

}