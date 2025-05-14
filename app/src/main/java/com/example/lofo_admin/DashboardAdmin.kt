package com.example.lofo_admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.LoFo.data.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardAdmin : AppCompatActivity() {

    lateinit var jumlahAkunTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_admin)
        val cardAkun = findViewById<LinearLayout>(R.id.cardAkun)
        jumlahAkunTextView= findViewById(R.id.jumlahAkunTextView)
        val cardVerifikasiHilang = findViewById<LinearLayout>(R.id.cardVerifikasiHilang)
        val cardVerifikasiTemuan = findViewById<LinearLayout>(R.id.cardVerifikasiTemuan)
        val cardLaporanHilang = findViewById<LinearLayout>(R.id.cardLaporanHilang)
        val cardLaporanTemuan = findViewById<LinearLayout>(R.id.cardLaporanTemuan)
        val backImage = findViewById<ImageView>(R.id.backImage)

        fetchJumlahAkun()


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
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Apakah Anda yakin ingin logout?")
            builder.setPositiveButton("Ya") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
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
                // Optional: tampilkan error ke user
            }
        }
    }
}