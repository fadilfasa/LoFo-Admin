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
import com.example.lofo_admin.model.BarangTemuan.LaporanBarangTemuan

class ListLaporanTemuan(
    val listBarang: ArrayList<LaporanBarangTemuan>,
    private val onUbahClick: (LaporanBarangTemuan) -> Unit,
    private val onHapusClick: (LaporanBarangTemuan) -> Unit
) : RecyclerView.Adapter<ListLaporanTemuan.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uploader: TextView = itemView.findViewById(R.id.uploaderLaporanTemuan_value)
        val nama: TextView = itemView.findViewById(R.id.namaLaporanBarangTemuan_value)
        val kategori: TextView = itemView.findViewById(R.id.kategoriLaporanTemuan_value)
        val tanggal: TextView = itemView.findViewById(R.id.tgl_laporan_temuan_value)
        val lokasi: TextView = itemView.findViewById(R.id.lokasi_laporan_temuan_value)
        val kabKota: TextView = itemView.findViewById(R.id.kab_kota_laporan_temuan_value)
        val noHP: TextView = itemView.findViewById(R.id.tv_phone_value)
        val detail: TextView = itemView.findViewById(R.id.detail_value)
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_LaporanBarangTemuan)
        val ubah: Button = itemView.findViewById(R.id.btn_ubahTemuan)
        val hapus: Button = itemView.findViewById(R.id.btn_delete)
        val status: TextView = itemView.findViewById(R.id.statusLaporanTemuan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_laporan_temuan, parent, false)
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