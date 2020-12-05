package com.example.smartphones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartphones.model.SmartphoneItem


class SmartphoneAdapter(val callback: FirstFragment): RecyclerView.Adapter<SmartphoneAdapter.SmartphoneViewHolder>() {

    private var smartphoneList = emptyList<SmartphoneItem>()

    fun updateAdapter(myList:List<SmartphoneItem>) {
        smartphoneList = myList
        notifyDataSetChanged()
    }

    inner class SmartphoneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val listImage = itemView.findViewById<ImageView>(R.id.imageRecycler)
        val listPrice = itemView.findViewById<TextView>(R.id.tvListPrice)
        val listName = itemView.findViewById<TextView>(R.id.tvListName)
        val click = itemView.setOnClickListener {
            callback.passItemInfo(smartphoneList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartphoneAdapter.SmartphoneViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.smartphonelist_item, parent, false)
        return SmartphoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmartphoneAdapter.SmartphoneViewHolder, position: Int) {
        val image = smartphoneList[position].image

        Glide.with(holder.itemView.context).load(image).fitCenter().into(holder.listImage)
        holder.listPrice.text=smartphoneList[position].price.toString()
        holder.listName.text=smartphoneList[position].name
    }

    override fun getItemCount()= smartphoneList.size

    interface PassSmartphoneData{
        fun passItemInfo(smartphone: SmartphoneItem)
    }

}