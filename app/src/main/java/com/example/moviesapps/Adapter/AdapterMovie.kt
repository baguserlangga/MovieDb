package com.example.moviesapps.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapps.Model.GenresModel
import com.example.moviesapps.Model.MovieModel
import com.example.moviesapps.R

class AdapterMovie (private val list : ArrayList<MovieModel>): RecyclerView.Adapter<AdapterMovie.pViewHolderAdapter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pViewHolderAdapter {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movies,parent,false)
        return pViewHolderAdapter(itemView)
    }

    override fun onBindViewHolder(holder: pViewHolderAdapter, position: Int) {

//        textEdit = "${list[position].mStok}"
        holder.textViewtitle.setText(list[position].title)
        holder.textViewtitle.setOnClickListener{

        }
        holder.itemView.setOnClickListener{
//            (holder.itemView.context as DaftarMaterialActivity?)!!.setFragment(1)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class pViewHolderAdapter(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewtitle : TextView =  itemView.findViewById(R.id.textViewtitle)

    }
//    https://www.youtube.com/watch?v=5mdV1hLbXzo&t=14s second 12:34


}