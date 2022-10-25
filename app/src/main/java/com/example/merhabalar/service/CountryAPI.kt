package com.example.merhabalar.service

import com.example.merhabalar.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    // https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    // base -> https://raw.githubusercontent.com/
    // ext -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET(value = "atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getDataWithRetro(): Single<List<Country>>
}
