package com.noisegain.hse_hw1.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.hse_hw1.R
import com.noisegain.hse_hw1.databinding.ItemHeader2Binding
import com.noisegain.hse_hw1.databinding.ItemHeaderBinding
import com.noisegain.hse_hw1.databinding.ItemInfoBinding
import com.noisegain.hse_hw1.databinding.ItemLangBinding
import com.noisegain.hse_hw1.model.Item
import com.noisegain.hse_hw1.model.OnClickListener
import java.lang.IllegalArgumentException

class ItemsAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_HEADER ->
                ViewHolderHeader(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            TYPE_INFO ->
                ViewHolderInfo(
                    ItemInfoBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            TYPE_HEADER2 ->
                ViewHolderHeader2(
                    ItemHeader2Binding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            TYPE_LANG ->
                ViewHolderLang(
                    ItemLangBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            else ->
                throw IllegalArgumentException("")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = items[position]) {
            is Item.Header ->
                (holder as? ViewHolderHeader)?.onBind(item)
            is Item.Info ->
                (holder as? ViewHolderInfo)?.onBind(item)
            is Item.Header2 ->
                (holder as? ViewHolderHeader2)?.onBind(item)
            is Item.Lang ->
                (holder as? ViewHolderLang)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int) =
        when(items[position]) {
            is Item.Header -> TYPE_HEADER
            is Item.Info -> TYPE_INFO
            is Item.Header2 -> TYPE_HEADER2
            is Item.Lang -> TYPE_LANG
        }

    override fun getItemCount() = items.size

    inner class ViewHolderHeader(
        private val viewBinding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Header) = with(viewBinding) {
            gitButton.setOnClickListener {
                this@ItemsAdapter.onClickListener
                    .redirTo(item.url)
            }
        }
    }

    class ViewHolderInfo(
        private val viewBinding: ItemInfoBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Info) = with(viewBinding) {
            textView.text = item.description
        }
    }

    inner class ViewHolderHeader2(
        private val viewBinding: ItemHeader2Binding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Header2) = with(viewBinding) {
            /*
            if (item.filtered) {
                filteredButton. как поставить картинку блин
            } else {
                filteredButton. как поставить картинку блин 2
            }
             */
            filterButton.setOnClickListener {
                this@ItemsAdapter.onClickListener.startFilterActivity()
            }
        }
    }

    class ViewHolderLang(
        private val viewBinding: ItemLangBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.Lang) = with(viewBinding) {
            lang1.text = item.title
            time1.text = item.time
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_INFO = 1
        private const val TYPE_HEADER2 = 2
        private const val TYPE_LANG = 3
    }
}