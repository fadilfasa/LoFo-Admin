package com.example.LoFo.data.api

import com.example.lofo_admin.model.AdminRequest
import com.example.lofo_admin.model.adminResponse
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
    fun logoutUser(@Body request: LogoutRequest) : Call<LogoutResponse>

    @GET("user/getAll")
    suspend fun getAllUser(): List<user>


//    @Multipart
//    @PUT("user/update/{id}")
//    fun updateUser(
//        @Path("id") id: String,
//        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
//    ): Call<User>



//    @PUT("user/update/{id}")
//    suspend fun updateUser(
//        @Path("id") id: String,
//        @Part("username") username: RequestBody,
//        @Part file: MultipartBody.Part,
//        @Part("email") email: RequestBody,
//        @Part("namaLengkap") namaLengkap: RequestBody,
//        @Part("jenisKelamin") jenisKelamin: RequestBody,
//        @Part("alamat") alamat: RequestBody,
//        @Part("noHP") noHP: RequestBody,
//        @Part("password") password: RequestBody,
//    ): List<User>



//    @GET("barang-temuan/getMyAll/{id}")
//    suspend fun getMyAllBarangTemuan( @Path("id") id: String): List<BarangTemuan>
//
//    @GET("barang-temuan/getOtherAll/{id}")
//    suspend fun getOtherAllBarangTemuan(
//        @Path("id") id: String,
//        @Query("kategoriBarang") kategoriBarang: String
//    ): List<BarangTemuan>

//    @Multipart
//    @POST("barang-temuan/upload")
//    fun uploadBarangTemuan(
//        @Part file: MultipartBody.Part,
//        @Part("nama") nama: RequestBody,
//    ) : Call<ResponseBody>

}