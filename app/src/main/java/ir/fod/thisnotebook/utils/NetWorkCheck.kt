package ir.fod.thisnotebook.utils


sealed class NetWorkCheck<T>(val data: T? = null, val message: String? = null ) {

class Success <T> (data: T?)       : NetWorkCheck<T>(data)

class Error <T>   (message: String?) : NetWorkCheck<T>(data = null , message = null)

class Lod   <T>                    : NetWorkCheck<T>()


}