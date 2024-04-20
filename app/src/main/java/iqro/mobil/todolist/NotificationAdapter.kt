package iqro.mobil.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import iqro.mobil.todolist.databinding.NotificationsLayoutBinding

class NotificationAdapter(context: Context, val list: List<String>) :
    Adapter<NotificationAdapter.MyViewHolder>() {
    private val context = context
    private var viewListener: ViewListener? = null

    inner class MyViewHolder(val binding: NotificationsLayoutBinding) : ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.notificationTv.text = title
        }

        init {
            binding.switcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.checkTv.text = "Bildirilsin"
                } else {
                    binding.checkTv.text = "Bildirilmasin"
                }
            }
            binding.root.startAnimation(AnimationUtils.loadAnimation(context, R.anim.transelate))
        }
    }

    fun setListener(listener: ViewListener) {
        viewListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NotificationsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }
}