package ir.fod.thisnotebook.utils

import androidx.recyclerview.widget.DiffUtil
import ir.fod.thisnotebook.model.ListModel

class ListDiffUtil (

   private  val oldList : List<ListModel> ,
   private  val newList : List<ListModel>

) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}