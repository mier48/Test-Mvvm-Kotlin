package com.example.pruebakotlin.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebakotlin.databinding.FragmentFavoritesBinding
import com.example.pruebakotlin.domain.model.Beer
import com.example.pruebakotlin.ui.adapter.BeerAdapter
import com.example.pruebakotlin.ui.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = LinearLayoutManager(activity)
        binding.favoritesBeerList.layoutManager = manager

        beerViewModel.favoritesBeers()

        beerViewModel.favBeerListModel.observe(viewLifecycleOwner, Observer {
            binding.favoritesBeerList.adapter =
                BeerAdapter(it, { beer -> onBeerSelected(beer) }, { beer -> removeFavorite(beer) })
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBeerSelected(beer: Beer) {
        val intent = Intent(activity, BeerDetailActivity::class.java)
        intent.putExtra("_id", beer.id)
        startActivity(intent)
    }

    private fun removeFavorite(beer: Beer) {
        beerViewModel.deleteFavorite(beer)
        beerViewModel.favoritesBeers()
    }
}