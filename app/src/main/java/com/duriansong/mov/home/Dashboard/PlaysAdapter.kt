package com.duriansong.mov.home.Dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duriansong.mov.R
import com.duriansong.mov.model.Plays

class PlaysAdapter(private var data: List<Plays>,
                   private var listener: (Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.AppViewHolder>() {

    lateinit var contextAdapter: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedview = layoutInflater.inflate(R.layout.row_item_play,parent,false)
        return AppViewHolder(inflatedview)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bindItem(
            data[position],
            listener,
            contextAdapter,
            position
        )
    }

    override fun getItemCount(): Int = data.size

    class AppViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private val tvTitle:TextView = view.findViewById(R.id.tc_kursi)

        private val tvImage:ImageView = view.findViewById(R.id.ic_poster_image)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context, position : Int){

            tvTitle.text = data.nama

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }
}
