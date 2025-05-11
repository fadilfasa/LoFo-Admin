package com.example.lofo_admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.ImageView
import android.widget.LinearLayout

class DashboardAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_admin)
        val cardAkun = findViewById<LinearLayout>(R.id.cardAkun)
        val cardVerifikasiHilang = findViewById<LinearLayout>(R.id.cardVerifikasiHilang)
        val cardVerifikasiTemuan = findViewById<LinearLayout>(R.id.cardVerifikasiTemuan)
        val cardLaporanHilang = findViewById<LinearLayout>(R.id.cardLaporanHilang)
        val cardLaporanTemuan = findViewById<LinearLayout>(R.id.cardLaporanTemuan)
        val backImage = findViewById<ImageView>(R.id.backImage)

        cardAkun.setOnClickListener {
            startActivity(Intent(this, KelolaAkun::class.java))
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
            startActivity(Intent(this, ListLaporanTemuan::class.java))
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
}