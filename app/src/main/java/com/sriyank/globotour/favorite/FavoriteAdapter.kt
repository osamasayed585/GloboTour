package com.sriyank.globotour.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.globotour.R
import com.sriyank.globotour.city.City

class FavoriteAdapter(val context: Context, var favoriteList: ArrayList<City>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentPosition: Int = -1
        private var currentFavorite: City? = null

        private val txvFavorite = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val  imvFavorite = itemView.findViewById<ImageView>(R.id.imv_city)

        fun bind(favorite: City, position: Int) {
            txvFavorite.text = favorite.name
            imvFavorite.setImageResource(favorite.imageId)

            currentPosition = position
            currentFavorite = favorite
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favoriteList[position]
        holder.bind(favorite, position)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}