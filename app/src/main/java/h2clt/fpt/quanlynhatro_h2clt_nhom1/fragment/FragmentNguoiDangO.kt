package h2clt.fpt.quanlynhatro_h2clt_home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.DialogThemKhachThueBinding
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.FragmentNguoiDangOBinding

class FragmentNguoiDangO : Fragment() {
     private lateinit var binding: FragmentNguoiDangOBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNguoiDangOBinding.inflate(LayoutInflater.from(context))
        // Inflate the layout for this fragment
        binding.imgAddNguoiThue.setOnClickListener {
            val build = AlertDialog.Builder(context)
            val dialog = DialogThemKhachThueBinding.inflate(LayoutInflater.from(context))
            build.setView(dialog.root)


            build.show()
        }



        return binding.root
    }

}