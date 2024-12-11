package com.example.sivaska.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sivaska.LoginActivity
import com.example.sivaska.R
import com.google.firebase.auth.FirebaseAuth

class LogoutFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Tombol Log Out
        val btnLogout: Button = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Logout dari Firebase
            auth.signOut()

            // Tampilkan pesan berhasil keluar
            Toast.makeText(context, "Anda telah keluar", Toast.LENGTH_SHORT).show()

            // Pindah ke halaman login
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            // Tutup fragment/aktivitas saat ini
            requireActivity().finish()
        }

        // Tombol Batal
        val btnCancel: Button = view.findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener {
            Toast.makeText(context, "Batal", Toast.LENGTH_SHORT).show()
            // Kembali ke halaman sebelumnya
            requireActivity().onBackPressed()
        }

        return view
    }
}
