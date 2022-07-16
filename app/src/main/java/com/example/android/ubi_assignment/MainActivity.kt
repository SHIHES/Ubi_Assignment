package com.example.android.ubi_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.android.ubi_assignment.databinding.ActivityMainBinding
import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.util.Logger

class MainActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportFragmentManager
            .setFragmentResultListener("requestKey",this) { requestKey, bundle ->
                val result = bundle.getParcelable<AirPollutionNetworkResult>("result")
            
                Logger.d("supportFragmentManager $result")
            }

    }
    
    override fun onResume() {
        super.onResume()
    

    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        
        searchView.queryHint = "Type here"
        
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
    
            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }
        })
    

            
            
                return super.onCreateOptionsMenu(menu)
    }
}