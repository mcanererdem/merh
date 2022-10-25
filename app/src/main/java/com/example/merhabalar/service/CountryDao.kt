package com.example.merhabalar.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.merhabalar.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertCountries(vararg country: Country): List<Long>

    @Query("SELECT * FROM Country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE uuid =:countryUUID")
    suspend fun getCountry(countryUUID: Int): Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()
}
