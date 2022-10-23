package com.example.merhabalar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merhabalar.model.Country
import com.example.merhabalar.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val countryAPIService = CountryAPIService()

    val countryList = MutableLiveData<List<Country>>()
    val countryError: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true

        compositeDisposable.add(
            countryAPIService.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        countryList.value = t
                        countryError.value = false
                        countryLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
