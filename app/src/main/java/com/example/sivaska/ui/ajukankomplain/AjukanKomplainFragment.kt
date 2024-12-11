package com.example.sivaska.ui.ajukankomplain

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sivaska.R
import com.example.sivaska.databinding.FragmentAjukanKomplainBinding
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

// Fragment untuk Ajukan Komplain

class AjukanKomplainFragment : Fragment() {

    private var _binding: FragmentAjukanKomplainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjukanKomplainBinding.inflate(inflater, container, false)

        // Spinner untuk memilih jenis kendaraan
        val jenisKendaraanList = arrayOf("Pilih Jenis Kendaraan", "Tronton", "Dumptruck", "Highblow", "Wingbox")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, jenisKendaraanList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerJenisKendaraan.adapter = spinnerAdapter

        // Klik tombol Ajukan
        binding.buttonAjukan.setOnClickListener {
            val nopol = binding.inputNopol.text.toString()
            val jenisKendaraan = binding.spinnerJenisKendaraan.selectedItem.toString()
            val keterangan = binding.Keterangan.text.toString()
            val inputSTNK = binding.inputSTNK.text.toString()

            // Validasi input
            if (nopol.isEmpty() || jenisKendaraan == "Pilih Jenis Kendaraan" || keterangan.isEmpty() || inputSTNK.isEmpty()) {
                Toast.makeText(requireContext(), "Harap isi semua data dengan benar!", Toast.LENGTH_SHORT).show()
            } else {
                ajukanKomplain(nopol, jenisKendaraan, keterangan, inputSTNK)
            }
        }

        return binding.root
    }

    private fun ajukanKomplain(nopol: String, jenisKendaraan: String, keterangan: String, inputSTNK: String) {
        val database = FirebaseDatabase.getInstance().getReference("komplain")
        val komplainId = database.push().key ?: UUID.randomUUID().toString()

        // Data yang akan disimpan
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val komplainData = mapOf(
            "nopol" to nopol,
            "jenis_kendaraan" to jenisKendaraan,
            "keterangan" to keterangan,
            "status" to "pending",
            "stnk" to inputSTNK
        )

        // Kirim data ke Firebase
        database.child(komplainId).setValue(komplainData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showSuccessDialog()
            } else {
                Toast.makeText(requireContext(), "Gagal mengajukan komplain!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_succes, null)

        builder.setView(view)
        builder.setCancelable(false)

        val dialog = builder.create()

        view.findViewById<View>(R.id.btn_ok).setOnClickListener {
            dialog.dismiss() // Tutup dialog
            binding.inputNopol.text?.clear() // Reset input Nopol
            binding.Keterangan.text?.clear() // Reset input Keterangan
            binding.inputSTNK.text?.clear() // Reset input STNK
            binding.spinnerJenisKendaraan.setSelection(0) // Reset spinner
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
