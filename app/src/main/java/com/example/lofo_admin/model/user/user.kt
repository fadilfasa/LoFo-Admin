package com.example.lofo_admin.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class user (
    val username: String,
    val email: String,
    val password: String,
    val namaLengkap: String,
    val jenisKelamin: String,
    val alamat: String,
    val noHP: String,
    val pictUrl: String,
):Parcelable