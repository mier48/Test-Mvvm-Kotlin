package com.example.pruebakotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlin.R
import com.example.pruebakotlin.domain.model.Beer

class BeerAdapter(
    private val beerList: List<Beer>,
    private val onClickListener: (Beer) -> Unit,
    private val addToFavoriteListener: (Beer) -> Unit
) : RecyclerView.Adapter<BeerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BeerViewHolder(layoutInflater.inflate(R.layout.beer, parent, false))
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = beerList[position]
        holder.render(item, onClickListener, addToFavoriteListener)
    }

    override fun getItemCount(): Int {
        if (!beerList.isNullOrEmpty()) {
            return beerList.size
        }
        return 0
    }
}