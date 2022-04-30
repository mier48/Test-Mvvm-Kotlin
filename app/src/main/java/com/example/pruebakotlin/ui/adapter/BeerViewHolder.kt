package com.example.pruebakotlin.ui.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlin.R
import com.example.pruebakotlin.databinding.BeerBinding
import com.example.pruebakotlin.domain.model.Beer
import com.squareup.picasso.Picasso

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = BeerBinding.bind(view)

    fun render(beer: Beer, onClickListener: (Beer) -> Unit, addToFavoriteListener: (Beer) -> Unit) {
        val context = binding.beerImage.context

        binding.beerName.text = beer.title
        binding.beerDescription.text = beer.description
        binding.beerAbv.text = beer.abv.toString() + "º"

        when {
            beer.abv <= 5 -> {
                binding.beerAbvCircle.setBackgroundResource(R.drawable.circle_green)
            }
            beer.abv <= 8 -> {
                binding.beerAbvCircle.setBackgroundResource(R.drawable.circle_yellow)
            }
            beer.abv <= 12 -> {
                binding.beerAbvCircle.setBackgroundResource(R.drawable.circle_orange)
            }
            else -> {
                binding.beerAbvCircle.setBackgroundResource(R.drawable.circle_red)
            }
        }

        if (beer.fav) {
            binding.addToFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_star
                )
            )
        } else {
            binding.addToFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_empty_star
                )
            )
        }

        val placeholder = ResourcesCompat.getDrawable(context.resources, R.mipmap.placeholder, null)
        Picasso.get().load(beer.image).placeholder(placeholder!!).into(binding.beerImage)

        binding.addToFavorite.setOnClickListener {
            addToFavoriteListener(beer)
        }

        itemView.setOnClickListener {
            onClickListener(beer)
        }
    }
}