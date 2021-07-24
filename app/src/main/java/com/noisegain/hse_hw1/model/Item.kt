package com.noisegain.hse_hw1.model

sealed interface Item {
    data class Header(val name: String, val y: String, val url: String): Item
    data class Info(val description: String): Item
    data class Header2(val title: String): Item
    data class Lang(val title: String, val time: String): Item
}

interface OnClickListener {
    fun redirTo(url: String)
}