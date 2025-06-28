package com.example.lofo_admin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.LoFo.data.api.ApiClient
import com.example.lofo_admin.R
import com.example.lofo_admin.ui.baranghilang.LaporanHilang
import com.example.lofo_admin.ui.baranghilang.VerifikasiHilang
import com.example.lofo_admin.ui.barangtemuan.LaporanTemuan
import com.example.lofo_admin.ui.barangtemuan.VerifikasiTemuan
import com.example.lofo_admin.ui.kelolaakun.KelolaAkun
import com.example.lofo_admin.model.logout.LogoutRequest
import com.example.lofo_admin.model.logout.LogoutResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.delay


class DashboardAdmin : AppCompatActivity() {

    lateinit var jumlahAkunTextView : TextView
    lateinit var jumlahVerifikasiHilang : TextView
    lateinit var jumlahVerifikasiTemuan : TextView
    lateinit var jumlahLaporanHilang : TextView
    lateinit var jumlahLaporanTemuan : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_admin)
        val cardAkun = findViewById<LinearLayout>(R.id.cardAkun)
        jumlahAkunTextView= findViewById(R.id.jumlahAkunTextView)
        jumlahVerifikasiHilang= findViewById(R.id.jumlahVerifikasiHilang)
        jumlahVerifikasiTemuan= findViewById(R.id.jumlahVerifikasiTemuan)
        jumlahLaporanHilang= findViewById(R.id.jumlahLaporanHilang)
        jumlahLaporanTemuan= findViewById(R.id.jumlahLaporanTemuan)
        val cardVerifikasiHilang = findViewById<LinearLayout>(R.id.cardVerifikasiHilang)
        val cardVerifikasiTemuan = findViewById<LinearLayout>(R.id.cardVerifikasiTemuan)
        val cardLaporanHilang = findViewById<LinearLayout>(R.id.cardLaporanHilang)
        val cardLaporanTemuan = findViewById<LinearLayout>(R.id.cardLaporanTemuan)
        val backImage = findViewById<ImageView>(R.id.backImage)

        fetchJumlahAkun()
        fetchJumlahVerifikasiHilang()
        fetchJumlahVerifikasiTemuan()
        fetchJumlahLaporanHilang()
        fetchJumlahLaporanTemuan()
        startAutoRefresh()

        cardAkun.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val response = ApiClient.apiService.getAllUser()
                    val intent = Intent(this@DashboardAdmin, KelolaAkun::class.java)
                    intent.putParcelableArrayListExtra("dataUser", ArrayList(response))
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this@DashboardAdmin, "Gagal mengambil data / data kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cardVerifikasiHilang.setOnClickListener {
            startActivity(Intent(this, VerifikasiHilang::class.java))
        }

        cardVerifikasiTemuan.setOnClickListener {
            startActivity(Intent(this, VerifikasiTemuan::class.java))
        }

        cardLaporanHilang.setOnClickListener {
            startActivity(Intent(this, LaporanHilang::class.java))
        }

        cardLaporanTemuan.setOnClickListener {
            startActivity(Intent(this, LaporanTemuan::class.java))
        }

        backImage.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Apakah Anda yakin ingin logout?")
            builder.setPositiveButton("Ya") { _, _ ->
                val user = "user"

                val request = LogoutRequest(user)
                ApiClient.apiService.logoutUser(request).enqueue(object : Callback<LogoutResponse> {
                    override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@DashboardAdmin, "Logout berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@DashboardAdmin, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@DashboardAdmin, "Logout gagal", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                        Toast.makeText(this@DashboardAdmin, "Kesalahan jaringan", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            builder.setNegativeButton("Batal", null)
            builder.show()
        }
    }

    fun fetchJumlahAkun() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getAllUser() // sesuaikan dengan implementasi retrofit kamu
                val jumlah = response.size

                withContext(Dispatchers.Main) {
                    jumlahAkunTextView.text = jumlah.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun fetchJumlahVerifikasiHilang() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getAllVerifikasiHilang()
                val jumlah = response.size

                withContext(Dispatchers.Main) {
                    jumlahVerifikasiHilang.text = jumlah.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun fetchJumlahVerifikasiTemuan() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getAllVerifikasiTemuan()
                val jumlah = response.size

                withContext(Dispatchers.Main) {
                    jumlahVerifikasiTemuan.text = jumlah.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Optional: tampilkan error ke user
            }
        }
    }

    fun fetchJumlahLaporanHilang() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getAllBarangHilang()
                val jumlah = response.size

                withContext(Dispatchers.Main) {
                    jumlahLaporanHilang.text = jumlah.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchJumlahLaporanTemuan() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getAllBarangTemuan()
                val jumlah = response.size

                withContext(Dispatchers.Main) {
                    jumlahLaporanTemuan.text = jumlah.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun startAutoRefresh() {
        lifecycleScope.launch {
            while (true) {
                fetchJumlahAkun()
                fetchJumlahVerifikasiHilang()
                fetchJumlahVerifikasiTemuan()
                fetchJumlahLaporanHilang()
                fetchJumlahLaporanTemuan()
                delay(5000)
            }
        }
    }
}