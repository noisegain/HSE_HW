package com.noisegain.hse_hw1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.noisegain.hse_hw1.adapter.ItemsAdapter
import com.noisegain.hse_hw1.databinding.ActivityMainBinding
import com.noisegain.hse_hw1.model.Item
import com.noisegain.hse_hw1.model.OnClickListener

val skills = listOf(Item.Lang("C++", "1 год", 1),
    Item.Lang("Python", "2 года", 2),
    Item.Lang("Swift", "1 год", 1),
    Item.Lang("Kotlin", "0.5 года", 0.5))

var filteredSkills = mutableListOf<Item.Lang>()

var is_filtered = false

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val itemsAdapter = ItemsAdapter(this)

    private val basic = listOf(
        Item.Header("Илья Пономаренко", "11 класс", "https://github.com/noisegain"),
        Item.Info("Приложение для чтения новостей\n" +
                "Группировка по интересам, выбор источников, тем"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filteredSkills.addAll(skills)
        binding = ActivityMainBinding.inflate(layoutInflater)
        itemsAdapter.items = getAdapter()
        with (binding) {
            setContentView(root)
            title = "О себе"
            with (rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemsAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        itemsAdapter.items = getAdapter()
    }

    private fun getAdapter() = basic + Item.Header2("Навыки", is_filtered) + filteredSkills

    override fun redirTo(url: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    override fun startFilterActivity() =
        startActivity(Intent(this, FilterActivity::class.java))
}