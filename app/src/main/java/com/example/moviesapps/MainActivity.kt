package com.example.moviesapps

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapps.Model.ResponseGetListGenre
import com.example.moviesapps.Service.RetrofitClient
import com.example.moviesapps.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        getGenre()


    }

    fun getGenre()
    {

        var number = 0

//        listJurnalBukuBesarDetail.clear()
//        rv.setHasFixedSize(true)
//        rv.layoutManager = LinearLayoutManager(this)
        val params: MutableMap<String, String> = HashMap()


        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
        params["language"] = "en-US"

        RetrofitClient.instance.getListGenre(params).enqueue(object : Callback<ResponseGetListGenre> {
            override fun onResponse(call: Call<ResponseGetListGenre>, response: Response<ResponseGetListGenre>) {
//                val user_array : JsonArray= response.body()
                val responsecode  =response.body().toString()

                Log.v("iniresponse" , response.body()!!.genres[0].name)
//                var daftarMaterial = ArrayList<SM_DaftarMaterial>()
//                daftarMaterial= response.body()!!.data
//                response.body()?.let{ list.addAll(daftarMaterial)}
//                val adapter = SM_DaftarMaterialAdapter(list)
//                rvDaftarStok.adapter = adapter

            }

            override fun onFailure(call: Call<ResponseGetListGenre>, t: Throwable) {
                Log.v("iniresponse" , t.message.toString())
            }

        })
    }
}