package com.example.moviesapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapps.Adapter.AdapterGenre
import com.example.moviesapps.Adapter.AdapterMovie
import com.example.moviesapps.Fragment.NowPlayingFragment
import com.example.moviesapps.Fragment.TopRatedMovieFragment
import com.example.moviesapps.Model.ResponseGetListGenre
import com.example.moviesapps.Model.GenresModel
import com.example.moviesapps.Model.MovieModel
import com.example.moviesapps.Model.ResponseMovie
import com.example.moviesapps.Service.RetrofitClient
import com.example.moviesapps.databinding.ActivityMainBinding
import com.example.moviesapps.databinding.BottomDialogGenresBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var bindings: BottomDialogGenresBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.textGenre.setOnClickListener{
            getGenre()
        }
        val adapter =myviewpageradapter(supportFragmentManager)
        adapter.addFragment(NowPlayingFragment(),"coba")
        adapter.addFragment(TopRatedMovieFragment(),"bisa")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
    class myviewpageradapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        private val Fragmentlist : MutableList<Fragment> =ArrayList()
        private val TitleList : MutableList<String> =ArrayList()


        override fun getCount(): Int {
            return Fragmentlist.size
        }

        override fun getItem(position: Int): Fragment {
            return Fragmentlist[position]
        }
        fun addFragment(fragment:Fragment, title:String){
            Fragmentlist.add(fragment)
            TitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return TitleList[position]
        }
    }
    fun getGenre()
    {
        val dialogView  =layoutInflater.inflate(R.layout.bottom_dialog_genres,null)
        dialog = BottomSheetDialog(this)
        dialog.setCancelable(false)
        bindings = BottomDialogGenresBinding.inflate(layoutInflater)
        var number = 0
        val params: MutableMap<String, String> = HashMap()
        bindings.rvGenre.setHasFixedSize(true)
        bindings.rvGenre.layoutManager = LinearLayoutManager(this)


        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
        params["language"] = "en-US"

        RetrofitClient.instance.getListGenre(params).enqueue(object : Callback<ResponseGetListGenre> {
            override fun onResponse(call: Call<ResponseGetListGenre>, response: Response<ResponseGetListGenre>) {
//                val user_array : JsonArray= response.body()
                val responsecode  =response.body().toString()

                Log.v("iniresponse" , response.body()!!.genres[0].name)
                var list = ArrayList<GenresModel>()
                list.addAll( response.body()!!.genres)

//                response.body()?.let{ list.addAll(response.body().genres)}
                val adapter = AdapterGenre(list)
                bindings.rvGenre.adapter = adapter

            }

            override fun onFailure(call: Call<ResponseGetListGenre>, t: Throwable) {
                Log.v("iniresponse" , t.message.toString())
            }

        })
        dialog.setContentView(dialogView)
        dialog.show()
    }

    fun getTopRatedMovie(rvMovie:RecyclerView)
    {
        var number = 0
        val params: MutableMap<String, String> = HashMap()
        rvMovie.setHasFixedSize(true)
        rvMovie.layoutManager = LinearLayoutManager(this)


        params["api_key"] = "7916ace8a965a1c3413cd5231af30364"
        params["language"] = "en-US"

        RetrofitClient.instance.getListTopratedMovie(params).enqueue(object : Callback<ResponseMovie> {
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
//                val user_array : JsonArray= response.body()
                val responsecode  =response.body().toString()

//                Log.v("iniresponse" , response.body()!!.genres[0].name)
                var list = ArrayList<MovieModel>()
                list.addAll( response.body()!!.results)

//                response.body()?.let{ list.addAll(response.body().genres)}
                val adapter = AdapterMovie(list)
                rvMovie.adapter = adapter

            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Log.v("iniresponse" , t.message.toString())
            }

        })
    }

}