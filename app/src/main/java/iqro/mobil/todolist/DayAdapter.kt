package iqro.mobil.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import iqro.mobil.todolist.databinding.DateLayoutBinding

class DayAdapter(context: Context, val list: List<Date>) : Adapter<DayAdapter.MyVIewHolder>() {
    private val context = context
    private var viewListener: ViewListener? = null

    inner class MyVIewHolder(val binding: DateLayoutBinding) : ViewHolder(binding.root) {
        fun bind(day: Int, weak: String) {
            binding.dayTv.text = day.toString()
            binding.weakTv.text = weak
        }


        init {
            binding.root.setOnClickListener {
                viewListener?.onClickDate(adapterPosition)

            }
            binding.root.startAnimation(AnimationUtils.loadAnimation(context, R.anim.trans))
        }

    }

    fun setListener(listener: ViewListener) {
        viewListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVIewHolder {
        return MyVIewHolder(
            DateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyVIewHolder, position: Int) {
        holder.bind(list[position].day, list[position].weak)
    }
}