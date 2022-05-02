package com.example.pruebakotlin.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebakotlin.databinding.FragmentMainBinding
import com.example.pruebakotlin.domain.model.Beer
import com.example.pruebakotlin.ui.adapter.BeerAdapter
import com.example.pruebakotlin.ui.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = LinearLayoutManager(activity)
        binding.beerList.layoutManager = manager

        //beerViewModel.onCreate()

        beerViewModel.beerListModel.observe(viewLifecycleOwner, Observer {
            binding.beerList.adapter =
                BeerAdapter(it, { beer -> onBeerSelected(beer) }, { beer -> addToFavorite(beer) })
        })

        beerViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        binding.searcherEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    beerViewModel.byName(s.toString().lowercase())
                } else {
                    beerViewModel.empty()
                }
            }
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
        intent.putExtra("_fav", beer.fav)

        startActivity(intent)
    }

    private fun addToFavorite(beer: Beer) {
        if (beer.fav) {
            beerViewModel.deleteFavorite(beer)
        } else {
            beerViewModel.addToFavorite(beer)
        }
    }
}