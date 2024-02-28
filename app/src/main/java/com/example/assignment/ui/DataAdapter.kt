package com.example.assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.databinding.ItemDataBinding
import com.example.assignment.data.model.Item



class DataAdapter  : RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {

    var items = mutableListOf<Item>()
    private var clickInterface: ClickInterface<Item>? = null

    fun updateMovies(movies: List<Item>) {
        this.items = movies.toMutableList()
        notifyItemRangeInserted(0, movies.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.view.tvTitle.text = item.title
        holder.view.tvDesc.text = HtmlCompat.fromHtml(item.description!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
        Glide
            .with(holder.view.imgDataImage.context)
            .load(item.media?.m)
            .centerCrop()
            .into(holder.view.imgDataImage)
        holder.view.movieCard.setOnClickListener {
            clickInterface?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItemClick(clickInterface: ClickInterface<Item>) {
        this.clickInterface = clickInterface
    }

    class ItemViewHolder(val view: ItemDataBinding) : RecyclerView.ViewHolder(view.root)
}

interface ClickInterface<T> {
    fun onClick(data: T)
}