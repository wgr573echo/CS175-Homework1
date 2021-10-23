package com.example.searchtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.res.Configuration
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val per = ArrayList<Perdog>()
    private lateinit var list :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<TextView>(R.id.back)
        button.setOnClickListener {
            val intent = Intent(this,NamedHome::class.java)
            startActivity(intent)
        }

        //  获取 RecyclerView 实例
        list = findViewById<RecyclerView>(R.id.list)
        list.setHasFixedSize(true)

        per.addAll(perdog)
        showRecyclerList()

    }

    private val perdog: ArrayList<Perdog>
        @SuppressLint("Recycle")
        get() {
            val dogname = resources.getStringArray(R.array.dogname)
            val doggender = resources.getStringArray(R.array.doggender)
            val dogdetail = resources.getStringArray(R.array.dogdetail)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val doglocation = resources.getStringArray(R.array.doglocation)
            val perdog = ArrayList<Perdog>()
            for (i in dogname.indices) {
                val dog = Perdog(dogname[i], doglocation[i],avatar.getResourceId(i, -1),doggender[i],  dogdetail[i], )
                perdog.add(dog)
            }
            return perdog
        }
        private fun showRecyclerList() {
            if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                list.layoutManager = GridLayoutManager(this, 2)
            else
                list.layoutManager = LinearLayoutManager(this)

            val listUserAdapter = ListDogAdapter(per)
            list.adapter = listUserAdapter

            // Call showSelectedUser function when item is clicked by user
            listUserAdapter.setOnItemClickCallback(object : ListDogAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Perdog) {
                    showSelectedUser(data)
                }

            })
        }
        // Send data as mentioned within parameter putExtra to DetailUserActivity
        private fun showSelectedUser(user: Perdog) {
            val manageDetailIntent = Intent(this@MainActivity, DetailDogActivity::class.java)
            manageDetailIntent.putExtra(DetailDogActivity.EXTRA_DETAIL_USER, user)
            startActivity(manageDetailIntent)
        }
}