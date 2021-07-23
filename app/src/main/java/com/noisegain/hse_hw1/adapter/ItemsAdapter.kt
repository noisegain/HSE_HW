package com.noisegain.hse_hw1.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.hse_hw1.model.Item

class ItemsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int) =
        when(items[position]) {
            is Item.Header -> TYPE_HEADER
            is Item.Info -> TYPE_INFO
            is Item.Header2 -> TYPE_HEADER2
            is Item.Lang -> TYPE_LANG
        }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_INFO = 1
        private const val TYPE_HEADER2 = 2
        private const val TYPE_LANG = 3
    }
}