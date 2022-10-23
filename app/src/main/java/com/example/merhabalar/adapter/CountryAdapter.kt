package com.example.merhabalar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.merhabalar.R
import com.example.merhabalar.model.Country
import com.example.merhabalar.util.downloadFromURL
import com.example.merhabalar.util.placeHodlerProgressBar
import com.example.merhabalar.view.fragments.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    class CountryHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.view.tv_name_itemCountry.text = countryList[position].name
        holder.view.tv_region_itemCountry.text = countryList[position].region

        holder.view.iv_itemCountry.downloadFromURL(
            countryList[position].imageURL!!,
            placeHodlerProgressBar(holder.view.context)
        )

        holder.itemView.setOnClickListener {
            val actionToCountryFragment =
                FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(actionToCountryFragment)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}
