package com.example.architecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.databinding.QuoteItemBinding
import com.example.architecture.entities.Quote

class Adapter(
    private val viewModel: QuoteViewModelList
) : RecyclerView.Adapter<ViewHolder>() {

    fun update(){
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel.getData()[position])
    }

    override fun getItemCount(): Int = viewModel.getData().size
}

class ViewHolder(
    private val binding: QuoteItemBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(quoteModel: Quote){
        binding.contentView.text = quoteModel.getText()
    }
}