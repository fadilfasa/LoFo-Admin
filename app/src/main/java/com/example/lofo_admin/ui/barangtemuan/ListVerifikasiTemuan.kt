package com.example.lofo_admin.ui.barangtemuan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lofo_admin.R
import com.example.lofo_admin.model.BarangTemuan.BarangTemuan

class ListVerifikasiTemuan (
    val listBarang: ArrayList<BarangTemuan>,
    private val onTerimaClick: (BarangTemuan) -> Unit,
    private val onHapusClick: (BarangTemuan) -> Unit
) : RecyclerView.Adapter<ListVerifikasiTemuan.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uploader: TextView = itemView.findViewById(R.id.uploaderTemuan_value)
        val nama: TextView = itemView.findViewById(R.id.namaBarangTemuan_value)
        val kategori: TextView = itemView.findViewById(R.id.kategori_value)
        val tanggal: TextView = itemView.findViewById(R.id.tgl_temuan_value)
        val lokasi: TextView = itemView.findViewById(R.id.lokasiTemuan_value)
        val kabKota: TextView = itemView.findViewById(R.id.kab_kota_value)
        val noHP: TextView = itemView.findViewById(R.id.tv_phone_value)
        val detail: TextView = itemView.findViewById(R.id.detail_value)
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_barangTemuan)
        val terima: Button = itemView.findViewById(R.id.btn_edit)
        val hapus: Button = itemView.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_verifikasi_temuan, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val barang = listBarang[position]

        holder.uploader.text = barang.uploader
        holder.nama.text = barang.namaBarang
        holder.kategori.text = barang.kategoriBarang
        holder.tanggal.text = barang.tanggalTemuan
        holder.lokasi.text = barang.tempatTemuan
        holder.kabKota.text = barang.kotaKabupaten
        holder.noHP.text = barang.noHP
        holder.detail.text = barang.informasiDetail

        Glide.with(holder.itemView.context)
            .load(barang.pictUrl)
            .placeholder(R.drawable.profil)
            .into(holder.imgItemPhoto)

        holder.terima.setOnClickListener {
            onTerimaClick(barang)
        }
        holder.hapus.setOnClickListener {
            onHapusClick(barang)
        }
    }

    override fun getItemCount(): Int = listBarang.size
}

