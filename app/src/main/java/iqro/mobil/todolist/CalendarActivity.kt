package iqro.mobil.todolist

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iqro.mobil.todolist.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView9.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {
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

        binding.imageView11.setOnClickListener {
            Intent(this, NotificationsActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.calendarView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim))
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