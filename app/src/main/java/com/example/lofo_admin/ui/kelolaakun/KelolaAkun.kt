package com.example.lofo_admin.ui.kelolaakun

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
import com.example.lofo_admin.model.user.user
import kotlinx.coroutines.launch

class KelolaAkun : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListKelolaAkun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kelola_akun)

        val backImage: ImageView = findViewById(R.id.backImage)
        val listUser = intent.getParcelableArrayListExtra<user>("dataUser") ?: arrayListOf()

        recyclerView = findViewById(R.id.recyclerViewUser)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListKelolaAkun(
            listUser,
            onUbahClick = { user ->
                Toast.makeText(
                    this@KelolaAkun,
                    "Gagal mengambil data / data kosong",
                    Toast.LENGTH_SHORT
                ).show()

            },
            onHapusClick = { selectedUser ->
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Konfirmasi Hapus")
                builder.setMessage("Apakah Anda yakin ingin menghapus akun ${selectedUser.username}?")

                builder.setPositiveButton("Hapus") { dialog, _ ->
                    lifecycleScope.launch {
                        try {
                            val response = ApiClient.apiService.deleteUser(selectedUser.username)
                            if (response.isSuccessful) {
                                adapter.removeUser(selectedUser)
                                Toast.makeText(
                                    this@KelolaAkun,
                                    "User berhasil dihapus",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@KelolaAkun,
                                    "Gagal menghapus user",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@KelolaAkun,
                                "Error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    dialog.dismiss()
                }

                builder.setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }

                builder.create().show()
            }

        )

        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()


        backImage.setOnClickListener {
            val intent = Intent(this, DashboardAdmin::class.java)
            startActivity(intent)
            finish()
        }
    }
}