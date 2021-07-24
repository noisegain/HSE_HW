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

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private val itemsAdapter = ItemsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        itemsAdapter.items = listOf(
            Item.Header("Илья Пономаренко", "11 класс", "https://github.com/noisegain"),
            Item.Info("Приложение для чтения новостей\n" +
                    "Группировка по интересам, выбор источников, тем"),
            Item.Header2("Навыки"),
            Item.Lang("C++", "1 год"),
            Item.Lang("Python", "2 года"),
            Item.Lang("Swift", "1 год"),
            Item.Lang("Kotlin", "0.5 года"),
            )
        with (binding) {
            setContentView(root)
            title = "О себе"
            with (rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemsAdapter
            }

        }
    }

    override fun redirTo(url: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}