package com.example.lofo_admin.model.BarangTemuan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarangTemuan(
    val idBarangTemuan: String,
    val uploader: String,
    val namaBarang: String,
    val kategoriBarang: String,
    val tanggalTemuan: String,
    val tempatTemuan: String,
    val kotaKabupaten: String,
    val noHP: String,
    val informasiDetail: String,
    val pictUrl : String,
    val status: String,
): Parcelable