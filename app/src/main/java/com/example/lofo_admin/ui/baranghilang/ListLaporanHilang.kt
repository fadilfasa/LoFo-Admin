package com.example.lofo_admin.ui.baranghilang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lofo_admin.R
import com.example.lofo_admin.model.barangHilang.LaporanBarangHilang

class ListLaporanHilang(
    val listBarang: ArrayList<LaporanBarangHilang>,
    private val onUbahClick: (LaporanBarangHilang) -> Unit,
    private val onHapusClick: (LaporanBarangHilang) -> Unit
) : RecyclerView.Adapter<ListLaporanHilang.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uploader: TextView = itemView.findViewById(R.id.uploaderLaporanHilang_value)
        val nama: TextView = itemView.findViewById(R.id.namaLaporanBarangHilang_value)
        val kategori: TextView = itemView.findViewById(R.id.kategoriLaporanHilang_value)
        val tanggal: TextView = itemView.findViewById(R.id.tgl_laporan_hilang_value)
        val lokasi: TextView = itemView.findViewById(R.id.lokasi_laporan_hilang_value)
        val kabKota: TextView = itemView.findViewById(R.id.kab_kota_laporan_hilang_value)
        val noHP: TextView = itemView.findViewById(R.id.tv_phone_value)
        val detail: TextView = itemView.findViewById(R.id.detail_value)
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_LaporanBarangHilang)
        val ubah: Button = itemView.findViewById(R.id.btn_ubahHilang)
        val hapus: Button = itemView.findViewById(R.id.btn_delete)
        val status: TextView = itemView.findViewById(R.id.statusLaporanHilang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_laporan_hilang, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val barang = listBarang[position]

        holder.uploader.text = barang.uploader
        holder.nama.text = barang.namaBarang
        holder.kategori.text = barang.kategoriBarang
        holder.tanggal.text = barang.tanggalHilang
        holder.lokasi.text = barang.tempatHilang
        holder.kabKota.text = barang.kotaKabupaten
        holder.noHP.text = barang.noHP
        holder.detail.text = barang.informasiDetail
        holder.status.text = barang.status

        Glide.with(holder.itemView.context)
            .load(barang.pictUrl)
            .placeholder(R.drawable.profil)
            .into(holder.imgItemPhoto)

        holder.ubah.setOnClickListener {
            onUbahClick(barang)
        }
        holder.hapus.setOnClickListener {
            onHapusClick(barang)
        }
    }

    override fun getItemCount(): Int = listBarang.size
}