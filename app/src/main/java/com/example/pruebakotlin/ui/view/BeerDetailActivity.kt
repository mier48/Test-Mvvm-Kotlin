package com.example.pruebakotlin.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.pruebakotlin.R
import com.example.pruebakotlin.databinding.ActivityBeerDetailBinding
import com.example.pruebakotlin.ui.viewmodel.BeerViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeerDetailBinding

    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: Int = intent.getIntExtra("_id", 0)

        beerViewModel.onCreate(id)

        beerViewModel.beerModel.observe(this, Observer { beer ->
            supportActionBar?.title = beer.title
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
                        this,
                        R.drawable.ic_star
                    )
                )
            } else {
                binding.addToFavorite.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_empty_star
                    )
                )
            }

            Picasso.get().load(beer.image).into(binding.beerImage)
        })
        beerViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}