package com.example.lofo_admin.model.barangHilang

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class LaporanBarangHilang(
    val idBarangHilang : String,
    val uploader: String,
    val namaBarang: String,
    val kategoriBarang: String,
    val tanggalHilang: String,
    val tempatHilang: String,
    val kotaKabupaten: String,
    val noHP: String,
    val informasiDetail: String,
    val pictUrl: String,
    val status: String,
) : Parcelable
