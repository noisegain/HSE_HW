package com.noisegain.hse_hw1.model

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolderBinder<in T : Item>(private val viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun onBind(item: Item)
    fun isAssignable(item: Item): Boolean = item.javaClass.isAssignableFrom(this.javaClass)
}