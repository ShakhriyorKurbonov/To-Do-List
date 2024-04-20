package iqro.mobil.todolist

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iqro.mobil.todolist.databinding.ActivityLoginBinding
import iqro.mobil.todolist.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("db", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "0")
        val password = sharedPreferences.getString("password", "0")

        binding.loginBtn.setOnClickListener {
            val enterEmail = binding.emailEt.text.trim().toString()
            val enterPassword = binding.passwordEt.text.trim().toString()
            if (enterEmail.isNotBlank() && enterPassword.isNotBlank()) {
                if (enterEmail == email && enterPassword == password) {
                    sharedPreferences.edit().putInt("n", 1).apply()
                    Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Parol yoki Email xato kiritildi", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Siz parol yoki emailni kiritmadingiz", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.textView.setOnClickListener {
            Intent(this, CreateAccountActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.imageView3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim))
        binding.emailEt.startAnimation(AnimationUtils.loadAnimation(this,R.anim.transelate_left_to_right))
        binding.passwordEt.startAnimation(AnimationUtils.loadAnimation(this,R.anim.transelate_right_to_left))
        binding.loginBtn.startAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_bottom_to_top))
        binding.textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.trans_bottom_to_top_500))

    }

    override fun onStart() {
        super.onStart()
        val n1 = sharedPreferences.getInt("n", 0)
        if (n1 != 0) {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

    }

}



