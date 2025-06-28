package com.example.lofo_admin.ui.baranghilang

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.LoFo.data.api.ApiClient
import com.example.lofo_admin.ui.DashboardAdmin
import com.example.lofo_admin.R
import kotlinx.coroutines.launch

class LaporanHilang : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListLaporanHilang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_laporan_hilang)

        val backImage: ImageView = findViewById(R.id.backImage)
        recyclerView = findViewById(R.id.recyclerViewLaporanHilang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListLaporanHilang(
            arrayListOf(),
            onUbahClick = { barang ->
                Toast.makeText(this, "Terima laporan: ${barang.namaBarang}", Toast.LENGTH_SHORT)
                    .show()
            },
            onHapusClick = { barang ->
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Yakin ingin menghapus laporan: ${barang.namaBarang}?")
                    .setPositiveButton("Hapus") { dialog, _ ->
                        lifecycleScope.launch {
                            try {
                                val response =
                                    ApiClient.apiService.deleteBarangHilang(barang.idBarangHilang)
                                if (response.isSuccessful) {
                                    val index = adapter.listBarang.indexOf(barang)
                                    if (index != -1) {
                                        adapter.listBarang.removeAt(index)
                                        adapter.notifyItemRemoved(index)
                                    }
                                    Toast.makeText(
                                        this@LaporanHilang,
                                        "Laporan berhasil dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@LaporanHilang,
                                        "Gagal menghapus laporan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(
                                    this@LaporanHilang,
                                    "Error: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("Batal", null)
                    .show()
            }


        )
        recyclerView.adapter = adapter

        getDataVerifikasi()

        backImage.setOnClickListener {
            val intent = Intent(this, DashboardAdmin::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getDataVerifikasi() {
        lifecycleScope.launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.getAllBarangHilang()
                adapter.apply {
                    listBarang.clear()
                    listBarang.addAll(response)
                    notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LaporanHilang, "Gagal memuat data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}