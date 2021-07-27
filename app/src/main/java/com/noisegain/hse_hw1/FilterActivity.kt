package com.noisegain.hse_hw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.noisegain.hse_hw1.adapter.FilterAdapter
import com.noisegain.hse_hw1.databinding.ActivityFilterBinding
import com.noisegain.hse_hw1.model.Item

class FilterActivity : AppCompatActivity() {

    private val filterAdapter = FilterAdapter()

    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        filterAdapter.items = filterAdapter.getAges(skills)
        with(binding) {
            setContentView(root)
            with(rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = filterAdapter
            }
            saveFilter.setOnClickListener {
                is_filtered = !filterAdapter.allSelected
                if (filterAdapter.filtered.isEmpty())
                    return@setOnClickListener
                filteredSkills =
                    (if (is_filtered) {
                        skillsFilter(filterAdapter.filtered)
                    } else {
                        skills
                    } as MutableList<Item.Lang>)
                finish()
            }
        }
    }

    private fun skillsFilter(filter: Set<Double>): List<Item.Lang> {
        val res = mutableListOf<Item.Lang>()
        skills.forEach {
            if (it.num in filter)
                res.add(it)
        }
        return res.sortedByDescending { it.num }
    }
}