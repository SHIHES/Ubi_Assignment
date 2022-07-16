package com.example.android.ubi_assignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.ubi_assignment.databinding.FragmentHomeBinding
import com.example.android.ubi_assignment.ext.getVmFactory
import com.example.android.ubi_assignment.logic.model.AirPollutionNetworkResult
import com.example.android.ubi_assignment.util.Logger

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
        val airResultAdapter = AirResultAdapter()
    
        resultRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        resultRv.adapter = airResultAdapter
    
    
        viewModel.airResult.observe(viewLifecycleOwner){
            it?.let {
                val result = Bundle()
                result.putParcelable("result", it)
                Logger.d("result from ${result.getParcelable<AirPollutionNetworkResult>("result")}")
                setFragmentResult("requestKey", result)
            }
        }
        
        viewModel.airResultGood.observe(viewLifecycleOwner) {
            it?.let {
                bannerAdapter.submitList(it)
            }
        }
        
        viewModel.airResultBad.observe(viewLifecycleOwner) {
            it?.let {
                Logger.d("viewModel.airResultBad $it")
                airResultAdapter.submitList(it)
            }
        }
        return binding.root
    }
}