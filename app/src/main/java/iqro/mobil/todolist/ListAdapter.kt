package iqro.mobil.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import iqro.mobil.todolist.databinding.ListLayoutBinding

class ListAdapter(context: Context, val list: List<ListData>) : Adapter<ListAdapter.MyHolder>() {
    private val context = context
    private var viewListener: ViewListener? = null


    inner class MyHolder(val binding: ListLayoutBinding) : ViewHolder(binding.root) {
        fun bind(time: String, listName: String) {
            binding.listTitle.text = listName
            binding.timeTv.text = time
        }

        init {
            binding.imageView5.setOnClickListener {
                viewListener?.onClickDelete(adapterPosition)
            }
            binding.root.startAnimation(AnimationUtils.loadAnimation(context, R.anim.transelate))
            binding.imageView6.setOnClickListener {
                viewListener?.onEdit(adapterPosition)
            }
        }

    }

    fun setListener(listener: ViewListener) {
        viewListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(list[position].time, list[position].listName)
    }
}