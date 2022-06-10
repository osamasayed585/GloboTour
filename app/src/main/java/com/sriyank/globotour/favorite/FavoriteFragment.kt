package com.sriyank.globotour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.globotour.R
import com.sriyank.globotour.city.VacationSpots


class FavoriteFragment : Fragment() {

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

        val adapter = FavoriteAdapter(context, VacationSpots.favoriteCityList)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.city_recycler_view)
        recyclerView?.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }
}
