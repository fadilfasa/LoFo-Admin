package com.example.lofo_admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lofoadmin.R
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class dashboardAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_admin)

    }
}