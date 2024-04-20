package iqro.mobil.todolist

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.NfcAdapter.OnTagRemovedListener
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import iqro.mobil.todolist.databinding.ActivityAddListBinding
import iqro.mobil.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listL: MutableList<ListData>
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("db", MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        val bornYear = sharedPreferences.getString("bornYear", "")
        val hour = java.util.Date().hours
        val tV = if (hour in 5..8) {
            "Xayirli tong "
        } else if (hour in 9..18) {
            "Xayirli kun "
        } else if (hour in 19..21) {
            "Xayirli kech "
        } else {
            "Xayirli tun "
        }
        binding.textView2.text = tV + name


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

        val dayAdapter = DayAdapter(this, list)
        val layMan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.dateRecyclerView.apply {
            adapter = dayAdapter
            layoutManager = layMan
        }

        dayAdapter.setListener(object : ViewListener {
            override fun onClickDate(position: Int) {

            }

            override fun onClickDelete(position: Int) {

            }

            override fun onCheck(position: Int) {

            }

            override fun onEdit(position: Int) {

            }
        })

        listL = mutableListOf<ListData>()


        val listAdapter = ListAdapter(this, listL)

        binding.listRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }



        binding.profileBtn.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView7.setOnClickListener {
            Intent(this, CalendarActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView.setOnClickListener {
            Intent(this, ProfileActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        binding.imageView8.setOnClickListener {
            Intent(this, NotificationsActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }




        binding.imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim))
        binding.textView2.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.transelate_left_to_right
            )
        )
        binding.searchView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim))
        binding.addList.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_rotate))
        binding.imageView2.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.trans_bottom_to_top
            )
        )
        binding.imageView7.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha500))
        binding.imageView8.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha500))
        binding.imageView10.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.scale_alpha500
            )
        )
        binding.profileBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_alpha500))
        binding.textView3.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.transelate_left_to_right
            )
        )

        binding.addList.setOnClickListener {
            val dialogViewBinding=ActivityAddListBinding.inflate(layoutInflater,null,false)
            val dialog=AlertDialog.Builder(this).setView(dialogViewBinding.root).create()
            dialogViewBinding.saveBtn.setOnClickListener {
                val addTitle=dialogViewBinding.listEt.text.toString().trim()
                val addCalendar=dialogViewBinding.calendarEt.text.toString().trim()
                val addTime=dialogViewBinding.timeEt.text.toString().trim()


                if (addTime.isNotEmpty()&&addTitle.isNotEmpty()&&addCalendar.isNotEmpty()){
                    val h=addTime.split("/")[0]
                    val m=addTime.split("/")[1]
                    listL.add(ListData("$h:$m",addTitle))
                }else{
                    Toast.makeText(this,"Barcha ma'lumotlar to'ldirilishi shart",Toast.LENGTH_SHORT).show()
                }
                listAdapter.notifyItemInserted(listL.size)
                dialog.dismiss()
            }
            dialog.show()
        }

        listAdapter.setListener(object : ViewListener {
            override fun onClickDate(position: Int) {

            }

            override fun onClickDelete(position: Int) {
                listL.removeAt(position)
                listAdapter.notifyItemRemoved(position)
            }

            override fun onCheck(position: Int) {

            }

            override fun onEdit(position: Int) {
                Toast.makeText(this@MainActivity,position.toString(),Toast.LENGTH_SHORT).show()
                val dialogViewBinding=ActivityAddListBinding.inflate(layoutInflater,null,false)
                val dialog=AlertDialog.Builder(this@MainActivity).setView(dialogViewBinding.root).create()
                dialogViewBinding.saveBtn.setOnClickListener {
                    val addTitle=dialogViewBinding.listEt.text.toString()
                    val addCalendar=dialogViewBinding.calendarEt.text.toString().trim()
                    val addTime=dialogViewBinding.timeEt.text.toString().trim()
                    if (addTime.isNotEmpty()&&addTitle.isNotEmpty()&&addCalendar.isNotEmpty()){
                        val h=addTime.split("/")[0]
                        val m=addTime.split("/")[1]
                        listL[position]=ListData("$h:$m",addTitle)
                    }else{
                        Toast.makeText(this@MainActivity,"Barcha ma'lumotlar to'ldirilishi shart",Toast.LENGTH_SHORT).show()
                    }
                    listAdapter.notifyItemInserted(position)
                    dialog.dismiss()
                }
                dialog.show()
            }
        })

    }

    override fun onStart() {
        val n = sharedPreferences.getInt("n", 0)
        if (n == 0) {
            finish()
        }
        super.onStart()
    }

}


