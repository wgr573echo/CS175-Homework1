package com.example.searchtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.searchtest.databinding.ActivityDetailDogBinding

class DetailDogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDogBinding

    companion object {
        const val EXTRA_DETAIL_USER = "extra_detail_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showDogDetail()

        val backButton = findViewById<ImageView>(R.id.iv_back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    /* Return value of data that has been sent from MainActivity
    * to be displayed on DetailUserActivity (second page/clicked item) */
    private fun showDogDetail() {
        val detailUser = intent.getParcelableExtra<Perdog>(EXTRA_DETAIL_USER) as Perdog


        // Glide Library (to manage image etc) https://github.com/bumptech/glide
        Glide.with(this)
            .load(detailUser.avatar)
            .circleCrop()
            .into(binding.imgItemPhoto)

        binding.tvItemRepository.text = detailUser.doggender
        binding.tvItemFollowers.text = detailUser.doglocation
        binding.tvItemFollowing.text = detailUser.dogdetail
    }
}