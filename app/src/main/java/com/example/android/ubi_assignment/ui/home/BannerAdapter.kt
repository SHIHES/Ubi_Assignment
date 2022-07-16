package com.example.android.ubi_assignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ubi_assignment.databinding.ItemBannerBinding
import com.example.android.ubi_assignment.logic.model.AirKPI

class BannerAdapter : ListAdapter<AirKPI, BannerAdapter.ItemVH>(DiffUtil()){
    
    
    class ItemVH(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root){
        
        fun bind(KPI: AirKPI){
            binding.itemBannerPM25.text = KPI.PM2dot5
            binding.itemBannerCounty.text = KPI.County
            binding.itemBannerSiteId.text = KPI.SiteId
            binding.itemBannerStatus.text = KPI.Status
            binding.itemBannerSiteName.text = KPI.SiteName
            
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    
    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val airKPI = getItem(position)
        holder.bind(KPI = airKPI)
    }
    
    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<AirKPI>() {
        override fun areItemsTheSame(oldItem: AirKPI, newItem: AirKPI): Boolean {
            return oldItem === newItem
        }
    
        override fun areContentsTheSame(oldItem: AirKPI, newItem: AirKPI): Boolean {
            return oldItem == newItem
        }
    
    }
}