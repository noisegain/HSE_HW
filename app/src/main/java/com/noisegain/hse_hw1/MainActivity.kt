package com.noisegain.hse_hw1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noisegain.hse_hw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Не успел доделать через recyclerView :(

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with (binding) {
            setContentView(root)
            title = "О себе"
            description.text = "Приложение для чтения новостей\nГруппировка по интересам, выбор источников, тем"
            gitButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/noisegain")))
            }
        }
    }
}