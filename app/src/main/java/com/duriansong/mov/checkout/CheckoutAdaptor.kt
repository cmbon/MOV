package com.duriansong.mov.checkout

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

class CheckoutAdaptor(private var data: List<Checkout>,
                      private var listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdaptor.appViewHolder>() {

    lateinit var contextAdapter: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): appViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedview = layoutInflater.inflate(R.layout.row_item_checkout,parent,false)
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
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga)


        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context : Context, position : Int) {


            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")){
                tvTitle.text = data.kursi
                tvTitle.setCompoundDrawables(null,null,null,null)
            } else {
                tvTitle.text = "Seat No. "+data.kursi
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }


    }
}
