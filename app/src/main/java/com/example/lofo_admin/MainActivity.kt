package com.example.lofo_admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lofo_admin.R

class MainActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var togglePasswordVisibility: ImageView
    private lateinit var loginButton: Button
    private lateinit var forgotPassword: TextView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility)
        loginButton = findViewById(R.id.loginButton)
        forgotPassword = findViewById(R.id.forgotPassword)

        togglePasswordVisibility.setOnClickListener {
            togglePasswordVisibility()
        }

        loginButton.setOnClickListener {
            handleLogin()
        }

        forgotPassword.setOnClickListener {
            Toast.makeText(this, "Silakan hubungi administrator untuk reset password.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordInput.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            togglePasswordVisibility.setImageResource(R.drawable.eye_off)
        } else {
            passwordInput.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            togglePasswordVisibility.setImageResource(R.drawable.eye_on)
        }
        isPasswordVisible = !isPasswordVisible
        passwordInput.setSelection(passwordInput.text.length)
    }

    private fun handleLogin() {
        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        if (username == "admin" && password == "admin123") {
            Toast.makeText(this, "Login berhasil sebagai Admin", Toast.LENGTH_SHORT).show()
            // Arahkan ke halaman admin
            val intent = Intent(this, DashboardAdmin::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
        }
    }
}


