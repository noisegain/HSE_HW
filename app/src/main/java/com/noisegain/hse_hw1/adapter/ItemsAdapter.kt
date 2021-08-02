package com.noisegain.hse_hw1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.hse_hw1.databinding.ItemHeader2Binding
import com.noisegain.hse_hw1.databinding.ItemHeaderBinding
import com.noisegain.hse_hw1.databinding.ItemInfoBinding
import com.noisegain.hse_hw1.databinding.ItemLangBinding
import com.noisegain.hse_hw1.model.Item
import com.noisegain.hse_hw1.model.OnClickListener
import com.noisegain.hse_hw1.model.ViewHolderBinder

class ItemsAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER ->
                ViewHolderHeader(ItemHeaderBinding.inflate(inflater, parent, false))
            TYPE_INFO ->
                ViewHolderInfo(ItemInfoBinding.inflate(inflater, parent, false))
            TYPE_HEADER2 ->
                ViewHolderHeader2(ItemHeader2Binding.inflate(inflater, parent, false))
            TYPE_LANG ->
                ViewHolderLang(ItemLangBinding.inflate(inflater, parent, false))
            else -> error("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderBinder<*>).onBind(items[position])

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is Item.Header -> TYPE_HEADER
            is Item.Info -> TYPE_INFO
            is Item.Header2 -> TYPE_HEADER2
            is Item.Lang -> TYPE_LANG
        }

    override fun getItemCount() = items.size

    inner class ViewHolderHeader(private val viewBinding: ItemHeaderBinding) :
        ViewHolderBinder<Item.Header>(viewBinding) {
        private fun onBind(item: Item.Header) = with(viewBinding) {
            gitButton.setOnClickListener {
                this@ItemsAdapter.onClickListener
                    .redirTo(item.url)
            }
        }

        override fun onBind(item: Item) {
            onBind(item as Item.Header)
        }
    }

    class ViewHolderInfo(private val viewBinding: ItemInfoBinding) :
        ViewHolderBinder<Item.Info>(viewBinding) {
        private fun onBind(item: Item.Info) = with(viewBinding) {
            textView.text = item.description
        }

        override fun onBind(item: Item) {
            onBind(item as Item.Info)
        }
    }

    inner class ViewHolderHeader2(private val viewBinding: ItemHeader2Binding) :
        ViewHolderBinder<Item.Header2>(viewBinding) {
        private fun onBind(item: Item.Header2) = with(viewBinding) {
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

        override fun onBind(item: Item) {
            onBind(item as Item.Header2)
        }
    }

    class ViewHolderLang(private val viewBinding: ItemLangBinding) :
        ViewHolderBinder<Item.Lang>(viewBinding) {
        private fun onBind(item: Item.Lang) = with(viewBinding) {
            lang1.text = item.title
            time1.text = item.time
        }

        override fun onBind(item: Item) {
            onBind(item as Item.Lang)
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_INFO = 1
        private const val TYPE_HEADER2 = 2
        private const val TYPE_LANG = 3
    }
}