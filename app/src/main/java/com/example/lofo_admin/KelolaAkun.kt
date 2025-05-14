package com.example.lofo_admin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lofo_admin.model.user.user

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
//                val intent = Intent(this@KelolaAkun, ::class.java)
//                intent.putExtra("barang", user)
//                startActivity(intent)
                Toast.makeText(this@KelolaAkun, "Gagal mengambil data / data kosong", Toast.LENGTH_SHORT).show()

            },
            onHapusClick = { user ->
                Toast.makeText(this@KelolaAkun, "Gagal mengambil data / data kosong", Toast.LENGTH_SHORT).show()

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
