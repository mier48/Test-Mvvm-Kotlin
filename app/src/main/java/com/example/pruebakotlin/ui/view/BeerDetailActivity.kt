package com.example.pruebakotlin.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.pruebakotlin.R
import com.example.pruebakotlin.databinding.ActivityBeerDetailBinding
import com.example.pruebakotlin.domain.model.Beer
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
        val fav: Boolean = intent.getBooleanExtra("_fav", false)
        val isFav: Boolean = intent.getBooleanExtra("_isFav", false)

        if (!isFav) {
            beerViewModel.onCreate(id)
        } else {
            beerViewModel.favoriteBeer(id)
        }

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

            if (fav || isFav) {
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

            binding.addToFavorite.setOnClickListener {
                addToFavorite(beer)
                if (fav || isFav) {
                    binding.addToFavorite.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this,
                            R.drawable.ic_empty_star
                        )
                    )
                } else {
                    binding.addToFavorite.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this,
                            R.drawable.ic_star
                        )
                    )
                }
            }

            val placeholder = ResourcesCompat.getDrawable(resources, R.mipmap.placeholder, null)
            Picasso.get().load(beer.image).placeholder(placeholder!!).into(binding.beerImage)
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

    private fun addToFavorite(beer: Beer) {
        if (beer.fav) {
            beerViewModel.deleteFavorite(beer)
        } else {
            beerViewModel.addToFavorite(beer)
        }
    }
}