package com.example.android.ubi_assignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.ubi_assignment.databinding.FragmentHomeBinding
import com.example.android.ubi_assignment.ext.getVmFactory

class HomeFragment : Fragment() {
    
    lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }
    
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        
        
        viewModel.airResult.observe(viewLifecycleOwner){
            it?.let {
                Log.d("Steven", "$it")
            }
        }
        return binding.root
    }
}