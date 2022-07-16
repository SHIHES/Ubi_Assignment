package com.example.android.ubi_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.android.ubi_assignment.databinding.ActivityMainBinding
import com.example.android.ubi_assignment.ext.getVmFactory
import com.example.android.ubi_assignment.ui.home.HomeViewModel


class MainActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityMainBinding
//    private val viewModel: HomeViewModel by viewModels{ getVmFactory()}
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        
//        supportFragmentManager
//            .setFragmentResultListener("requestKey",this) { requestKey, bundle ->
//                val result = bundle.getParcelable<AirPollutionNetworkResult>("result")
//
//                Logger.d("supportFragmentManager $result")
//            }

    }
    
    private fun setupToolbar() {
        val toolbar = binding.mainActivityToolbar
        toolbar.inflateMenu(R.menu.menu)
        toolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.action_search -> {
                }
            }
            false
        }
    
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }
    
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
    
        })
        return super.onCreateOptionsMenu(menu)
    }
    
}