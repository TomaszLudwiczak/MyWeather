package com.ludwiczak.myweather.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ludwiczak.myweather.R
import com.ludwiczak.myweather.domain.Location
import kotlinx.android.synthetic.main.location_item.view.*
import java.util.*

class LocationAdapter(private val onClick: ((Location) -> Unit)? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: MutableList<Location> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocationHolder(LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as LocationHolder).bind(list[position], onClick)



    class LocationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(location: Location, onClick: ((Location) -> Unit)?){
            itemView.locationName.text = String.format(
                Locale.getDefault(),
                itemView.context.getString(R.string.location_item),
                location.localizedName,
                location.country?.localizedName,
                location.administrativeArea?.localizedName
            )
            with(itemView){
                setOnClickListener {
                    onClick?.invoke(location)
                }
            }
        }
    }
}