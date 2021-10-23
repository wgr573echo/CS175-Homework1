package com.example.searchtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchtest.databinding.ItemRowBinding

class ListDogAdapter(private val listUser: ArrayList<Perdog>) : RecyclerView.Adapter<ListDogAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    // List of items that will be displayed on MainActivity (Homepage)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, full, avatar) = listUser[position]

        // Glide Library (to manage image etc) https://github.com/bumptech/glide
        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        holder.binding.dogItemUsername.text = username
        holder.binding.dogItemDetail.text = full
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Perdog)
    }
}