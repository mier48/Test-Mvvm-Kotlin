package com.example.pruebakotlin.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pruebakotlin.R
import com.example.pruebakotlin.databinding.ActivityBeerMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val manager = LinearLayoutManager(this)
//        //val decoration = DividerItemDecoration(this, manager.orientation)
//        binding.beerList.layoutManager = manager
//        //binding.beerList.addItemDecoration(decoration)
//
//        beerViewModel.onCreate()
//
//        beerViewModel.beerListModel.observe(this, Observer {
//            binding.beerList.adapter =
//                BeerAdapter(it, { beer -> onBeerSelected(beer) }, { beer -> addToFavorite(beer) })
//        })
//
//        beerViewModel.isLoading.observe(this, Observer {
//            binding.progress.isVisible = it
//        })
//
//        binding.searcherEditText.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s != null) {
//                    beerViewModel.byName(s.toString().lowercase())
//                }
//            }
//        })
    }
}