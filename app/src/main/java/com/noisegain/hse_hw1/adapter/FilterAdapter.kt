package com.noisegain.hse_hw1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noisegain.hse_hw1.databinding.ItemFilterBinding
import com.noisegain.hse_hw1.model.Item

class FilterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Pair<Double, String>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var filtered = mutableSetOf<Double>()

    var allSelected = false

    fun selectAll(boolean: Boolean) {
        allSelected = boolean
        notifyItemRangeChanged(1, items.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_HEADER ->
                ViewHolderHeader(
                    ItemFilterBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            else ->
                ViewHolderBody (
                    ItemFilterBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            HEADER ->
                (holder as? ViewHolderHeader)?.onBind()
            else ->
                (holder as? ViewHolderBody)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int) =
        when(items[position]) {
            HEADER -> TYPE_HEADER
            else -> TYPE_BODY
        }

    override fun getItemCount() = items.size

    fun getAges(list: List<Item.Lang>): List<Pair<Double, String>> =
        (mutableSetOf(HEADER) +
                list.map {
                    Pair(it.num, it.time)
                }.toSet())
            .toList()
            .sortedBy { it.first }

    inner class ViewHolderHeader(
        private val viewBinding: ItemFilterBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind() = with(viewBinding) {
            checkBox.text = "Все"
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                selectAll(isChecked)
            }
        }
    }

    inner class ViewHolderBody(
        private val viewBinding: ItemFilterBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(age: Pair<Double, String>) = with(viewBinding) {
            checkBox.text = age.second
            checkBox.isChecked = allSelected
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    filtered.add(age.first)
                } else {
                    filtered.remove(age.first)
                }
            }
        }

        fun onSelect(boolean: Boolean) {
            viewBinding.checkBox.isChecked = boolean
        }
    }

    companion object {
        private val HEADER = Pair(.0, "")
        private const val TYPE_HEADER = 0
        private const val TYPE_BODY = 1
    }
}