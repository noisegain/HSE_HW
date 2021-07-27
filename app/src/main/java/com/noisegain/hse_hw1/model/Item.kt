package com.noisegain.hse_hw1.model

sealed interface Item {
    data class Header(val name: String, val y: String, val url: String): Item
    data class Info(val description: String): Item
    data class Header2(val title: String, val filtered: Boolean): Item
    data class Lang(val title: String, val time: String, val num: Double): Item {
        constructor(title: String, time: String, num: Int) : this(title, time, num.toDouble())
    }
}

interface OnClickListener {
    fun redirTo(url: String)
    fun startFilterActivity()
}