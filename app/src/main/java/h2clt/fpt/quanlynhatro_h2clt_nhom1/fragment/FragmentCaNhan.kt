package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityDangNhap
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.THONG_TIN_DANG_NHAP
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentCaNhanBinding


class FragmentCaNhan:Fragment() {
    private lateinit var binding: FragmentCaNhanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaNhanBinding.inflate(inflater,container,false)
        binding.btnDangXuat.setOnClickListener {
            val intent = Intent(activity, ActivityDangNhap::class.java)
            activity?.finish()
            startActivity(intent)
        }
        return binding.root
    }
}