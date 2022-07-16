package com.example.android.ubi_assignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        
        val bannerRv = binding.fragmentHomeBannerRv
        val bannerAdapter = BannerAdapter()
        
        bannerRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bannerRv.adapter = bannerAdapter
        
        val resultRv = binding.fragmentHomeResultRv
        val airResultAdapter = AirResultAdapter(
            AirResultAdapter.OnClickListener{
                Toast.makeText(requireContext(), "This is $it position", Toast.LENGTH_SHORT).show()
            }
        )
    
        resultRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        resultRv.adapter = airResultAdapter
    
    
        viewModel.airResult.observe(viewLifecycleOwner){
            it?.let {
                bannerAdapter.submitList(it.records)
                airResultAdapter.submitList(it.records)
                Log.d("Steven", "$it")
            }
        }
        return binding.root
    }
}