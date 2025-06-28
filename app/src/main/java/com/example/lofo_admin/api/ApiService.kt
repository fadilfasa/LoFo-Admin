package com.example.LoFo.data.api

import com.example.lofo_admin.model.AdminRequest
import com.example.lofo_admin.model.BarangTemuan.BarangTemuan
import com.example.lofo_admin.model.BarangTemuan.LaporanBarangTemuan
import com.example.lofo_admin.model.adminResponse
import com.example.lofo_admin.model.barangHilang.BarangHilang
import com.example.lofo_admin.model.barangHilang.LaporanBarangHilang
import com.example.lofo_admin.model.logout.LogoutRequest
import com.example.lofo_admin.model.logout.LogoutResponse
import com.example.lofo_admin.model.user.user
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("admin/login")
    fun loginUser(@Body request: AdminRequest): Call<adminResponse>

    @POST("user/logout")
    fun logoutUser(@Body request: LogoutRequest): Call<LogoutResponse>

    @GET("user/getAll")
    suspend fun getAllUser(): List<user>

    @DELETE("user/delete/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Unit>

    @GET("barang-Hilang/getAll")
    suspend fun getAllBarangHilang(): List<LaporanBarangHilang>

    @GET("barang-Hilang/getAllVerifikasi")
    suspend fun getAllVerifikasiHilang(): List<BarangHilang>

    @PUT("barang-Hilang/update/{id}")
    suspend fun terimaBarangHilang(
        @Path("id") id: String,
        @Body updateData: Map<String, String>
    ): Response<Unit>

    @DELETE("barang-Hilang/delete/{id}")
    suspend fun deleteBarangHilang(@Path("id") id: String): Response<Unit>

    @GET("barang-Temuan/getAll")
    suspend fun getAllBarangTemuan(): List<LaporanBarangTemuan>

    @GET("barang-Temuan/getAllVerifikasi")
    suspend fun getAllVerifikasiTemuan(): List<BarangTemuan>

    @PUT("barang-Temuan/update/{id}")
    suspend fun terimaBarangTemuan(
        @Path("id") id: String,
        @Body updateData: Map<String, String>
    ): Response<Unit>

    @DELETE("barang-Temuan/delete/{id}")
    suspend fun deleteBarangTemuan(@Path("id") id: String): Response<Unit>
}
