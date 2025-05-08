package com.example.lofo_admin

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginTitle: TextView
    private lateinit var logoImage: ImageView
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var togglePasswordVisibility: ImageView
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        loginTitle = findViewById(R.id.loginTitle)
        logoImage = findViewById(R.id.logoImage)
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility)
        loginButton = findViewById(R.id.loginButton)

        // Set up click listeners
        togglePasswordVisibility.setOnClickListener {
            // Toggle password visibility
            togglePasswordVisibility()
        }

        loginButton.setOnClickListener {
            // Handle login button click
            handleLogin()
        }
    }

    private fun togglePasswordVisibility() {
        // Check the current input type and toggle the password visibility
        if (passwordInput.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            // Show password
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT
            togglePasswordVisibility.setImageResource(R.drawable.eye_on) // Eye open icon
        } else {
            // Hide password
            passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            togglePasswordVisibility.setImageResource(R.drawable.eye_off) // Eye closed icon
        }

        // Move the cursor to the end after toggling visibility
        passwordInput.setSelection(passwordInput.text.length)
    }

    private fun handleLogin() {
        // Get the input username and password
        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        // Check if the username and password match the admin credentials
        if (username == "admin" && password == "12345") {
            // Proceed with login logic (e.g., open DashboardAdminActivity)
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            // Navigate to the DashboardAdminActivity
            val intent = Intent(this, dashboardAdmin::class.java)
            startActivity(intent)
            finish() // Optional: Close the LoginActivity so the user can't return to it
        } else {
            // Show error message if the credentials don't match
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}
