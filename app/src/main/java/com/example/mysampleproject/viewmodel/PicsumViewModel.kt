package com.example.mysampleproject.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysampleproject.model.Picsum
import com.example.mysampleproject.network.RetrofitInstance
import com.example.mysampleproject.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PicsumViewModel : ViewModel() {
    var picsumDataList = MutableLiveData<List<Picsum>>()
    var picsumData = MutableLiveData<Picsum>()


    fun getAllPicsumData() {
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitInstance.getDataPicsumList().enqueue(object : Callback<List<Picsum>> {
            override fun onResponse(call: Call<List<Picsum>>, response: Response<List<Picsum>>) {
                picsumDataList.value = response.body()
            }

            override fun onFailure(call: Call<List<Picsum>>, t: Throwable) {
            }

        })
    }

    fun getPicsumData(id: String) {
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitInstance.getDataPicsum(id).enqueue(object : Callback<Picsum> {
            override fun onResponse(call: Call<Picsum>, response: Response<Picsum>) {
                picsumData.value = response.body()
            }

            override fun onFailure(call: Call<Picsum>, t: Throwable) {
            }

        })
    }


}