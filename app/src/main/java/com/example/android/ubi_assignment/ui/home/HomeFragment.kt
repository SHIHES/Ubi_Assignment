package com.example.android.ubi_assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.ubi_assignment.databinding.FragmentHomeBinding
import com.example.android.ubi_assignment.ext.getVmFactory
import com.example.android.ubi_assignment.logic.model.AirKPI
import com.example.android.ubi_assignment.util.Logger

class HomeFragment : Fragment() {
    
    private lateinit var binding: FragmentHomeBinding
    
    private val viewModel by activityViewModels<SharedViewModel> { getVmFactory() }
    
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
            }
        }
        
        viewModel.airResultGood.observe(viewLifecycleOwner) {
            it?.let {
                bannerAdapter.submitList(it)
            }
        }
        
        viewModel.airResultBad.observe(viewLifecycleOwner) {
            it?.let {
                airResultAdapter.setData(it as MutableList<AirKPI>)
            }
        }
        
        viewModel.searchStage.observe(viewLifecycleOwner) {
            when(it) {
                false -> { // ????????????
                    binding.fragmentHomeResultRv.visibility = View.VISIBLE
                    binding.fragmentHomeHint.visibility = View.GONE
                }
                true -> { // ????????????
                    binding.fragmentHomeResultRv.visibility = View.GONE
                    binding.fragmentHomeHint.visibility = View.VISIBLE
                    binding.fragmentHomeHint.text = "???????????? \n ???????????????????????????"
                }
            }

        }
        
        viewModel.searchText.observe(viewLifecycleOwner) {
            it?.let { searchText ->
                airResultAdapter.filter.filter(searchText)
                if (it.isNullOrEmpty()){
                    binding.fragmentHomeResultRv.visibility = View.GONE
                    binding.fragmentHomeHint.visibility = View.VISIBLE
                    binding.fragmentHomeHint.text = "???????????????"
                }
            }
        }
        
        airResultAdapter.searchStatus.observe(viewLifecycleOwner) {
            it?.let {
                Logger.d("searchStatus $it")
                when(it) {
                    SearchResult.YES -> {
                        binding.fragmentHomeResultRv.visibility = View.VISIBLE
                        binding.fragmentHomeHint.visibility = View.INVISIBLE
                        binding.fragmentHomeHint.text = ""
                    }
                    SearchResult.No -> {
                        binding.fragmentHomeResultRv.visibility = View.GONE
                        binding.fragmentHomeHint.visibility = View.VISIBLE
                        binding.fragmentHomeHint.text = "???????????????(${viewModel.searchText.value})?????????"
                    }
                    
                    SearchResult.EMPTY -> {
                        binding.fragmentHomeResultRv.visibility = View.GONE
                        binding.fragmentHomeHint.visibility = View.VISIBLE
                        binding.fragmentHomeHint.text = "???????????????"
                    }
                }
            }
        }
        return binding.root
    }
}