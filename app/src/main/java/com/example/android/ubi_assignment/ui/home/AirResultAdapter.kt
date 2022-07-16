package com.example.android.ubi_assignment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ubi_assignment.databinding.ItemAirresultBinding
import com.example.android.ubi_assignment.logic.model.AirKPI

class AirResultAdapter (
    private val onClickListener: OnClickListener
) : ListAdapter<AirKPI, AirResultAdapter.ItemVH>(DiffUtil()){
    
    
    class ItemVH(private val binding: ItemAirresultBinding) : RecyclerView.ViewHolder(binding.root){
        
        fun bind(KPI: AirKPI){
            binding.itemAirresultSitePN25.text = KPI.PM2dot5
            binding.itemAirresultSiteCounty.text = KPI.County
            binding.itemAirresultSiteId.text = KPI.SiteId
            binding.itemAirresultStatus.text = KPI.Status
            binding.itemAirresultSiteName.text = KPI.SiteName
        }
    }
    
    class OnClickListener(val clickListener: (position: Int) -> Unit){
        fun onClick(position: Int) = clickListener(position)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(ItemAirresultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    
    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val airKPI = getItem(position)
        holder.bind(KPI = airKPI)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position = position)
        }
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