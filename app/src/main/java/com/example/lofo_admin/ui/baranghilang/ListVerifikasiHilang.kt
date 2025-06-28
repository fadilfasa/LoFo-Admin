package com.example.lofo_admin.ui.baranghilang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lofo_admin.R
import com.example.lofo_admin.model.barangHilang.BarangHilang


class ListVerifikasiHilang(
    val listBarang: ArrayList<BarangHilang>,
    private val onTerimaClick: (BarangHilang) -> Unit,
    private val onHapusClick: (BarangHilang) -> Unit
) : RecyclerView.Adapter<ListVerifikasiHilang.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val uploader: TextView = itemView.findViewById(R.id.uploaderHilang_value)
        val nama: TextView = itemView.findViewById(R.id.namaBarangHilang_value)
        val kategori: TextView = itemView.findViewById(R.id.kategori_value)
        val tanggal: TextView = itemView.findViewById(R.id.tgl_hilang_value)
        val lokasi: TextView = itemView.findViewById(R.id.lokasi_value)
        val kabKota: TextView = itemView.findViewById(R.id.kab_kota_value)
        val noHP: TextView = itemView.findViewById(R.id.tv_phone_value)
        val detail: TextView = itemView.findViewById(R.id.detail_value)
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_barangHilang)
        val terima: Button = itemView.findViewById(R.id.btn_edit)
        val hapus: Button = itemView.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_verifikasi_hilang, parent, false)
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
