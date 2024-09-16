package ir.fod.thisnotebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.fod.thisnotebook.databinding.ItemNoteBinding
import ir.fod.thisnotebook.model.ListModel
import ir.fod.thisnotebook.model.ResultListModel
import ir.fod.thisnotebook.utils.ListDiffUtil


class ListNoteAdapter (

    private val onClick : (ListModel) -> Unit

) : RecyclerView.Adapter<ListNoteAdapter.MyViewHolder>() {

    private var dataEmpty = emptyList <ListModel>()

    class MyViewHolder(private val binding : ItemNoteBinding ):RecyclerView.ViewHolder(binding.root){

        fun  bind(listModel: ListModel, onClick: (ListModel) -> Unit){

            binding.list = listModel
            binding.executePendingBindings()

            binding.root.setOnClickListener{

                onClick(listModel)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding    = ItemNoteBinding.inflate(layoutInflater , parent , false)

        return MyViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {

        return dataEmpty.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(dataEmpty[position] , onClick)

    }

    fun setData(resultListModel: ResultListModel){

        val diffList = DiffUtil.calculateDiff(ListDiffUtil( dataEmpty , resultListModel.result ))

        dataEmpty = resultListModel.result

        diffList.dispatchUpdatesTo(this)



    }


    }


