package com.example.lofo_admin.ui.baranghilang

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import com.example.LoFo.data.api.ApiClient
import com.example.lofo_admin.ui.DashboardAdmin
import com.example.lofo_admin.R

class VerifikasiHilang : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListVerifikasiHilang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verifikasi_hilang)

        val backImage: ImageView = findViewById(R.id.backImage)
        recyclerView = findViewById(R.id.recyclerViewVerifikasiHilang)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListVerifikasiHilang(
            arrayListOf(),
            onTerimaClick = { barang ->
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Terima laporan barang hilang: ${barang.namaBarang}?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        lifecycleScope.launch {
                            try {
                                val updateData = mapOf("status" to "Diterima")
                                val response = ApiClient.apiService.terimaBarangHilang(barang.idBarangHilang, updateData)

                                if (response.isSuccessful) {
                                    val index = adapter.listBarang.indexOf(barang)
                                    if (index != -1) {
                                        adapter.listBarang.removeAt(index)
                                        adapter.notifyItemRemoved(index)
                                    }
                                    Toast.makeText(this@VerifikasiHilang, "Laporan diterima", Toast.LENGTH_SHORT).show()

                                } else {
                                    Toast.makeText(this@VerifikasiHilang, "Gagal menerima laporan", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(this@VerifikasiHilang, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("Batal", null)
                    .show()
            },
            onHapusClick = { barang ->
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Penolakan")
                    .setMessage("Tolak laporan barang hilang: ${barang.namaBarang}?")
                    .setPositiveButton("Tolak") { dialog, _ ->
                        lifecycleScope.launch {
                            try {
                                val updateData = mapOf("status" to "Ditolak")
                                val response = ApiClient.apiService.terimaBarangHilang(barang.idBarangHilang, updateData)

                                if (response.isSuccessful) {
                                    val index = adapter.listBarang.indexOf(barang)
                                    if (index != -1) {
                                        adapter.listBarang.removeAt(index)
                                        adapter.notifyItemRemoved(index)
                                    }
                                    Toast.makeText(this@VerifikasiHilang, "Laporan ditolak", Toast.LENGTH_SHORT).show()
                            } else {
                                    Toast.makeText(this@VerifikasiHilang, "Gagal menolak laporan", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(this@VerifikasiHilang, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
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
                val response = ApiClient.apiService.getAllVerifikasiHilang()
                adapter.listBarang.clear()
                adapter.listBarang.addAll(response)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(this@VerifikasiHilang, "Data Kosong: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
