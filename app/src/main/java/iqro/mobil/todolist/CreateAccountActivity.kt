package iqro.mobil.todolist

import android.R
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import iqro.mobil.todolist.databinding.ActivityCreateAccountBinding
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CreateAccountActivity : AppCompatActivity() {
    private var formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var personImage: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personImage = binding.pearsonImage
        val addImage = binding.addImage
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLUE))

        sharedPreferences = getSharedPreferences("db", MODE_PRIVATE)

        addImage.setOnClickListener {
            ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }



        binding.saveBtn.setOnClickListener {
            val bornDate = binding.bornDateEt.text.toString()
            val name = binding.nameEt.text.toString()
            val password = binding.passwordEt.text.trim().toString()
            val email = binding.emailEt.text.trim().toString()
            if (name.isNotBlank() && bornDate.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {

                val bornYear = bornDate.split("/")[2].toInt()
                val bornMonth = bornDate.split("/")[1].toInt()
                val bornDay = bornDate.split("/")[0].toInt()

                val year = LocalDate.now().toString()
                val a = year.subSequence(0..3).toString()
                val localYear = a.toInt()
                val b = year.subSequence(5..6).toString()
                val localMonth = b.toInt()
                val c = year.subSequence(8..9).toString()
                val localDay = c.toInt()
                var age = -1
                if (bornYear < localYear) {
                    age = localYear - bornYear
                    if (bornMonth > localMonth) {
                        age -= 1
                    } else if (bornMonth == localMonth) {
                        if (bornDay > localDay) {
                            age -= 1
                        }
                    }
                }

                Toast.makeText(this, "$age", Toast.LENGTH_SHORT).show()


                sharedPreferences.edit().clear().apply()
                sharedPreferences.edit().putString("age", age.toString()).apply()
                sharedPreferences.edit().putString("email", email).apply()
                sharedPreferences.edit().putString("password", password).apply()
                sharedPreferences.edit().putString("name", name).apply()
                sharedPreferences.edit().putString("bornDate", "$bornDay/$bornMonth/$bornYear")
                    .apply()
                Toast.makeText(this, "Malumotlar muaffaqiyatli saqlandi", Toast.LENGTH_SHORT).show()
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            } else {
                Toast.makeText(this, "Barcha malumotlar to'ldirilishi shart!!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.pearsonImage.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.scale_anim))
        binding.addImage.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.scale_alpha500))
        binding.nameEt.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.transelate_left_to_right))
        binding.emailEt.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.transelate_left_to_right))
        binding.bornDateEt.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.transelate_right_to_left))
        binding.passwordEt.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.transelate_right_to_left))
        binding.saveBtn.startAnimation(AnimationUtils.loadAnimation(this, iqro.mobil.todolist.R.anim.trans_bottom_to_top))
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val da = data?.data
        personImage.setImageURI(da)
        val da1 = da.toString()
        Intent(this, LoginActivity::class.java).apply {
            putExtra("dd", da1)
        }
    }
}





