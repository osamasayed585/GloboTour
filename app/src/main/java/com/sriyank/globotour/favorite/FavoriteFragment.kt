package com.sriyank.globotour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sriyank.globotour.R
import com.sriyank.globotour.city.City
import com.sriyank.globotour.city.VacationSpots
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {

    private lateinit var favoriteCityList: ArrayList<City>
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View?) {
        val context = requireContext()
        favoriteCityList = VacationSpots.favoriteCityList as ArrayList
        favoriteAdapter = FavoriteAdapter(context, favoriteCityList)
        recyclerView = view?.findViewById<RecyclerView>(R.id.city_recycler_view)!!
        recyclerView?.adapter = favoriteAdapter

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager


        itemTouchHelper.attachToRecyclerView(recyclerView)


    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            // called when the item is dragged

            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(favoriteCityList, fromPosition, toPosition)

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // called when the item is swiped.

            val position = viewHolder.adapterPosition
            val deleteCity: City = favoriteCityList[position]

            deleteItem(position)
            updateCityList(deleteCity, false)

            Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, deleteCity)
                    updateCityList(deleteCity, true)
                }
                .show()

        }

        private fun undoDelete(position: Int, deleteCity: City) {
            favoriteCityList.add(position, deleteCity)
            favoriteAdapter.notifyItemInserted(position)
            favoriteAdapter.notifyItemRangeChanged(position, favoriteCityList.size)
        }

        private fun updateCityList(deleteCity: City, isFavorite: Boolean) {
            val cityList = VacationSpots.cityList!!
            val position = cityList.indexOf(deleteCity)
            cityList[position].isFavorite = isFavorite
        }

        private fun deleteItem(position: Int) {
            favoriteCityList.removeAt(position)
            favoriteAdapter.notifyItemRemoved(position)
            favoriteAdapter.notifyItemRangeChanged(position, favoriteCityList.size)
        }

    })
}
