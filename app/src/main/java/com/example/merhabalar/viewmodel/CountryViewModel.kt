package com.example.merhabalar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merhabalar.model.Country

class CountryViewModel : ViewModel() {
    val country = MutableLiveData<Country>()

    fun getDataFromRoom() {

        val country1 = Country(
            name = "Skyrim",
            capital = "Whiterun",
            region = "Tamriel",
            currency = "Money",
            language = "English",
            imageURL = "www.asasasas.com"
        )

        country.value = country1
    }
}