package com.example.android.ubi_assignment.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ubi_assignment.R
import com.example.android.ubi_assignment.databinding.ItemAirresultBinding
import com.example.android.ubi_assignment.logic.model.AirKPI
import com.example.android.ubi_assignment.util.Logger

class AirResultAdapter : ListAdapter<AirKPI, AirResultAdapter.ItemVH>(DiffUtil()){
    
    
    class ItemVH(private val binding: ItemAirresultBinding) : RecyclerView.ViewHolder(binding.root){
        
        fun bind(KPI: AirKPI, position: Int){
            binding.itemAirresultSitePN25.text = KPI.PM2dot5
            binding.itemAirresultSiteCounty.text = KPI.County
            binding.itemAirresultSiteId.text = KPI.SiteId
            binding.itemAirresultStatus.text = KPI.Status
            binding.itemAirresultSiteName.text = KPI.SiteName
            binding.itemAirresultDetailImage.setOnClickListener {
                Toast.makeText(itemView.context, "This is $position position", Toast.LENGTH_SHORT).show()
            }
            
            if (KPI.Status == itemView.context.getString(R.string.good_status)){
                binding.itemAirresultStatus.text = itemView.context.getString(R.string.good_status_content)
                binding.itemAirresultDetailImage.visibility = View.GONE
            } else {
                binding.itemAirresultDetailImage.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(ItemAirresultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    
    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val airKPI = getItem(position)
        holder.bind(KPI = airKPI, position)
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