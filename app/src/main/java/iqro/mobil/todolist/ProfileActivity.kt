package iqro.mobil.todolist

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iqro.mobil.todolist.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("db", MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "")
        val bornDate = sharedPreferences.getString("bornDate", "Malumot topilmadi")
        val email = sharedPreferences.getString("email", "")
        val age = sharedPreferences.getString("age", "malumot olib bolmadi")

        binding.nameTv.text = name
        binding.bornDateTv.text = bornDate
        binding.emailTv.text = email
        binding.ageTv.text = age

        binding.closeBtn.setOnClickListener {
            sharedPreferences.edit().putInt("n", 0).apply()
            Intent(this,LoginActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }

        binding.imageView19.setOnClickListener {
            Intent(this, CalendarActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView18.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView17.setOnClickListener {
            Intent(this, NotificationsActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim))
        binding.closeBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim))
        binding.imageView15.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.trans_bottom_to_top
            )
        )
        binding.imageView16.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.imageView17.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.imageView18.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.imageView19.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.textView4.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.transelate_left_to_right
            )
        )
        binding.textView5.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tr_ltr_250))
        binding.textView6.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tr_ltr_500))
        binding.textView7.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tr_ltr_750))
        binding.textView8.startAnimation(AnimationUtils.loadAnimation(this, R.anim.tr_ltr_1000))
        binding.nameTv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha_1000))
        binding.bornDateTv.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha_1000
            )
        )
        binding.emailTv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha_1000))
        binding.ageTv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha_1000))
        binding.switcher.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha_1000))


    }
}