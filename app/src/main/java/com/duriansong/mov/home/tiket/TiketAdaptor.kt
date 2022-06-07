package com.duriansong.mov.home.tiket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duriansong.mov.R
import com.duriansong.mov.model.Checkout
import java.text.NumberFormat
import java.util.*

class TiketAdaptor(private var data: List<Checkout>,
                   private var listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<TiketAdaptor.appViewHolder>() {

    lateinit var contextAdapter: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): appViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedview = layoutInflater.inflate(R.layout.row_item_checkout_white,parent,false)
        return appViewHolder(inflatedview)
    }

    override fun onBindViewHolder(holder: appViewHolder, position: Int) {
        holder.bindItem(
            data[position],
            listener,
            contextAdapter,
            position
        )
    }

    override fun getItemCount(): Int = data.size

    class appViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private val tvTitle: TextView = view.findViewById(R.id.tc_kursi)


        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context : Context, position : Int) {

            tvTitle.setText("Seat No."+data.kursi)


            itemView.setOnClickListener {
                listener(data)
            }
        }


    }
}
