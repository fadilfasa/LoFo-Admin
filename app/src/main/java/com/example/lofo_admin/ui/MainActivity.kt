package com.example.lofo_admin.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.LoFo.data.api.ApiClient
import com.example.lofo_admin.R
import com.example.lofo_admin.model.AdminRequest
import com.example.lofo_admin.model.SharedPrefHelper
import com.example.lofo_admin.model.adminResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var togglePasswordVisibility: ImageView
    private lateinit var loginButton: Button

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility)
        loginButton = findViewById(R.id.loginButton)

        togglePasswordVisibility.setOnClickListener {
            togglePasswordVisibility()
        }

        loginButton.setOnClickListener {
            val adminInput = usernameInput.text.toString()
            val passInput = passwordInput.text.toString()
            val request = AdminRequest(adminInput, passInput)

            ApiClient.apiService.loginUser(request).enqueue(object : Callback<adminResponse> {
                override fun onResponse(
                    call: Call<adminResponse>,
                    response: Response<adminResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        val user = loginResponse?.lofoadmin
                        val token = loginResponse?.token
                        SharedPrefHelper.saveString(this@MainActivity, "TOKEN", token ?: "")
                        SharedPrefHelper.saveBoolean(this@MainActivity, "IS_LOGGED_IN", true)
                        SharedPrefHelper.saveUser(this@MainActivity, user)
                        SharedPrefHelper.saveToken(this@MainActivity, token ?: "")

                        Toast.makeText(this@MainActivity, "Login berhasil!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, DashboardAdmin::class.java))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        errorBody?.let {
                            val jsonObj = JSONObject(it)
                            val errorMessage = jsonObj.getString("message")
                            Toast.makeText(this@MainActivity, "Login gagal: $errorMessage", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<adminResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            togglePasswordVisibility.setImageResource(R.drawable.eye_off)
        } else {
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            togglePasswordVisibility.setImageResource(R.drawable.eye_on)
        }
        isPasswordVisible = !isPasswordVisible
        passwordInput.setSelection(passwordInput.text.length)
    }

}