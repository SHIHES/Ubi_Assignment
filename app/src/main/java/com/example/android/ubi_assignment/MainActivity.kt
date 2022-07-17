package com.example.android.ubi_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.example.android.ubi_assignment.databinding.ActivityMainBinding
import com.example.android.ubi_assignment.ext.getVmFactory
import com.example.android.ubi_assignment.ui.home.SharedViewModel
import com.example.android.ubi_assignment.util.Logger


class MainActivity : AppCompatActivity() {
    
    lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels{ getVmFactory()}
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

    }
    
    private fun setupToolbar() {
        val toolbar = binding.mainActivityToolbar
        toolbar.inflateMenu(R.menu.menu)
        
        val item = toolbar.menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.searchView_hint)
        
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.getSearchText(newText)
                }
            
                return false
            }
        
        })
        
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                viewModel.getSearchStatus(true)
                return true
            }
    
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                viewModel.getSearchStatus(false)
                return true
            }
        })

    }
    
    
}