package iqro.mobil.todolist

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import iqro.mobil.todolist.databinding.ActivityNotificationsBinding

class NotificationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = mutableListOf<Date>(
            Date(1, "Dush"),
            Date(2, "Sesh"),
            Date(3, "Chor"),
            Date(4, "Pay"),
            Date(5, "Jum"),
            Date(6, "Shan"),
            Date(7, "Yak"),
            Date(8, "Dush"),
            Date(9, "Sesh"),
            Date(10, "Jum"),
        )

        val dateAdapter = DayAdapter(this, list)
        val layMan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.dateRecyclerView.apply {
            adapter = dateAdapter
            layoutManager = layMan
        }

        val listL = mutableListOf<String>()

        for (i in 1..10) {
            listL.add("List $i")
        }

        val notificationAdapter = NotificationAdapter(this, listL)
        binding.notificationRec.apply {
            adapter = notificationAdapter
            layoutManager = LinearLayoutManager(this@NotificationsActivity)
        }

        notificationAdapter.setListener(object : ViewListener {
            override fun onClickDate(position: Int) {

            }

            override fun onClickDelete(position: Int) {

            }

            override fun onCheck(position: Int) {
                listL[position]
            }

            override fun onEdit(position: Int) {

            }
        })

        binding.imageView13.setOnClickListener {
            Intent(this, CalendarActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView12.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView9.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }


        binding.imageView14.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.trans_bottom_to_top
            )
        )
        binding.imageView9.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha500))
        binding.imageView11.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.imageView12.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.imageView13.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )

    }
}