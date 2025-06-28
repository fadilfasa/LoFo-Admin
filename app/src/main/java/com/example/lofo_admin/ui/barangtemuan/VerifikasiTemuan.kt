package com.example.lofo_admin.ui.barangtemuan

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
import com.example.LoFo.data.api.ApiClient
import com.example.lofo_admin.ui.DashboardAdmin
import com.example.lofo_admin.R
import kotlinx.coroutines.launch

class VerifikasiTemuan : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListVerifikasiTemuan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verifikasi_temuan)

        val backImage: ImageView = findViewById(R.id.backImage)
        recyclerView = findViewById(R.id.recyclerViewVerifikasiTemuan)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListVerifikasiTemuan(
            arrayListOf(),
            onTerimaClick = { barang ->
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Terima laporan barang temuan: ${barang.namaBarang}?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        lifecycleScope.launch {
                            try {
                                val updateData = mapOf("status" to "Diterima")
                                val response = ApiClient.apiService.terimaBarangTemuan(barang.idBarangTemuan, updateData)

                                if (response.isSuccessful) {
                                    val index = adapter.listBarang.indexOf(barang)
                                    if (index != -1) {
                                        adapter.listBarang.removeAt(index)
                                        adapter.notifyItemRemoved(index)
                                    }
                                    Toast.makeText(this@VerifikasiTemuan, "Laporan diterima", Toast.LENGTH_SHORT).show()
                                } else {
                                    val error = response.errorBody()?.string()
                                    Toast.makeText(this@VerifikasiTemuan, "Gagal menerima laporan: $error", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(this@VerifikasiTemuan, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
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
                    .setMessage("Tolak laporan barang temuan: ${barang.namaBarang}?")
                    .setPositiveButton("Tolak") { dialog, _ ->
                        lifecycleScope.launch {
                            try {
                                val updateData = mapOf("status" to "Ditolak")
                                val response = ApiClient.apiService.terimaBarangTemuan(barang.idBarangTemuan, updateData)

                                if (response.isSuccessful) {
                                    val index = adapter.listBarang.indexOf(barang)
                                    if (index != -1) {
                                        adapter.listBarang.removeAt(index)
                                        adapter.notifyItemRemoved(index)
                                    }
                                    Toast.makeText(this@VerifikasiTemuan, "Laporan ditolak", Toast.LENGTH_SHORT).show()

                            } else {
                                    val error = response.errorBody()?.string()
                                    Toast.makeText(this@VerifikasiTemuan, "Gagal menolak laporan: $error", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(this@VerifikasiTemuan, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
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
                val response = ApiClient.apiService.getAllVerifikasiTemuan()
                adapter.listBarang.clear()
                adapter.listBarang.addAll(response)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(this@VerifikasiTemuan, "Data Kosong: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
