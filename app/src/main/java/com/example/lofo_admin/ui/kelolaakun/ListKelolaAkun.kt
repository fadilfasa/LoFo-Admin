package com.example.lofo_admin.ui.kelolaakun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lofo_admin.R
import com.example.lofo_admin.model.user.user

class ListKelolaAkun (
    private val listUser: ArrayList<user>,
    private val onUbahClick: (user) -> Unit,
    private val onHapusClick: (user) -> Unit
    ) : RecyclerView.Adapter<ListKelolaAkun.ListViewHolder>() {

        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val username: TextView = itemView.findViewById(R.id.tv_username_value)
            val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_profile)
            val email: TextView = itemView.findViewById(R.id.tv_email_value)
            val namaLengkap: TextView = itemView.findViewById(R.id.tv_full_name_value)
            val jenisKelamin: TextView = itemView.findViewById(R.id.tv_gender_value)
            val alamat: TextView = itemView.findViewById(R.id.tv_address_value)
            val noHP: TextView = itemView.findViewById(R.id.tv_phone_value)

            val ubah: Button = itemView.findViewById(R.id.btn_edit)
            val hapus: Button = itemView.findViewById(R.id.btn_delete)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_list_kelola_akun, parent, false)
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val user = listUser[position]


            holder.username.text = user.username
            holder.email.text = user.email
            holder.namaLengkap.text = user.namaLengkap
            holder.jenisKelamin.text = user.jenisKelamin
            holder.alamat.text = user.alamat
            holder.noHP.text = user.noHP

            Glide.with(holder.itemView.context)
                .load(user.pictUrl)
                .placeholder(R.drawable.profil)
                .into(holder.imgItemPhoto)

            holder.ubah.setOnClickListener {
                onUbahClick(user)
            }
            holder.hapus.setOnClickListener {
                onHapusClick(user)
            }

        }

        override fun getItemCount(): Int = listUser.size

    fun removeUser(user: user) {
        val position = listUser.indexOf(user)
        if (position != -1) {
            listUser.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}