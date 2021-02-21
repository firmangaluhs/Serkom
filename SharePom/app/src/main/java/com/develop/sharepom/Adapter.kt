package com.develop.sharepom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mapbox.mapboxsdk.geometry.LatLng
//menghubungkan data dengan rv
class Adapter(private val listData: List<ModelPom>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tvNama)
        var tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        var ivGambar: ImageView = itemView.findViewById(R.id.ivGambar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.tvNama.text = data.Nama
        holder.tvAlamat.text = data.Alamat
        holder.tvDeskripsi.text = data.Deskripsi
        Glide.with(holder.itemView.context)
            .load(data.Gambar)
            .apply(RequestOptions().fitCenter())
            .into(holder.ivGambar)

        holder.itemView.setOnClickListener() {
            val context = holder.itemView.context

            context.startActivity(
                Intent(context, MapboxActivity::class.java)
                    .apply { putExtra(MapboxActivity.EXTRA_COORDINATE, data.coordinate.toLatLng()) }
            )
        }
    }
}

private fun List<Double>?.toLatLng(): LatLng = LatLng(this?.get(0)!!, this[1])