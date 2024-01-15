package com.example.architecture.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture.ArchitectureApplication
import com.example.architecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as ArchitectureApplication).viewModel

        viewModel.observe(this){
            binding.quoteText.text = it.getText()
        }

        binding.getQuote.setOnClickListener{
            viewModel.getQuote()
        }
    }
}