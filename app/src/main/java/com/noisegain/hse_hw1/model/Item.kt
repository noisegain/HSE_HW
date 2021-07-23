package com.noisegain.hse_hw1.model

sealed interface Item {
    data class Header(val title: String): Item
    data class Info(val description: String): Item
    data class Header2(val title: String): Item
    data class Lang(val title: String): Item
}