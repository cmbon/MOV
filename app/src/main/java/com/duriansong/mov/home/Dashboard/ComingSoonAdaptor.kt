package com.duriansong.mov.home.Dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duriansong.mov.R
import com.duriansong.mov.model.Film

class ComingSoonAdaptor(private var data: List<Film>,
                        private var listener: (Film) -> Unit)
    : RecyclerView.Adapter<ComingSoonAdaptor.AppViewHolder>() {

    lateinit var contextAdapter: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedview = layoutInflater.inflate(R.layout.row_item_coming_soon,parent,false)
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
        private val tvGenre:TextView = view.findViewById(R.id.tv_genre)
        private val tvRate:TextView = view.findViewById(R.id.tv_rate)

        private val tvImage:ImageView = view.findViewById(R.id.ic_poster_image)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context, position : Int){

            tvTitle.text = data.judul
            tvGenre.text = data.genre
            tvRate.text = data.rating

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }
}
