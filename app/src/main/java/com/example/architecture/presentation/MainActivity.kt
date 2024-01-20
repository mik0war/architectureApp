package com.example.architecture.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture.ArchitectureApplication
import com.example.architecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuoteViewModel
    private lateinit var listViewModel: QuoteViewModelList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as ArchitectureApplication).viewModel

        setUpSingleQuote()

        listViewModel = (application as ArchitectureApplication).listViewModel

        setUpRecyclerView()

        listViewModel.getQuote()
    }

    private fun setUpSingleQuote(){
        viewModel.observe(this){
            binding.quoteText.text = it?.getText()

            Log.e("test", binding.quoteText.text.toString())
        }

        binding.getQuote.setOnClickListener{
            viewModel.getQuote()
        }
    }
    private fun setUpRecyclerView(){
        val adapter = Adapter(listViewModel)

        listViewModel.observe(this){
            adapter.update()
        }

        binding.recyclerView.adapter = adapter
    }
}