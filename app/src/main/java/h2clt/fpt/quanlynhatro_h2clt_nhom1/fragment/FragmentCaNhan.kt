package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCaiDatThongTin
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityThongTinChuNha
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityDangNhap
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentCaNhanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin


class FragmentCaNhan:Fragment() {
    private lateinit var binding: FragmentCaNhanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaNhanBinding.inflate(inflater,container,false)
        val tenDao = AdminDao(binding.root.context).getHoTenAdmin()
        val sdtDao = AdminDao(binding.root.context).getSDTAdmin()
        binding.tvTenChuNha.text = tenDao
        binding.tvSDT.text = "SĐT: "+sdtDao
        binding.btnDangXuat.setOnClickListener {
            val intent = Intent(activity, ActivityDangNhap::class.java)
            activity?.finish()
            startActivity(intent)
        }
//        binding.tvCaiDat.setOnClickListener {
//            val intent = Intent(activity, ActivityCaiDatThongTin::class.java)
////            activity?.finish()
//            startActivity(intent)
//        }
        binding.tvThongTinChuNha.setOnClickListener {
            val intent = Intent(activity, ActivityThongTinChuNha::class.java)
//            activity?.finish()
            startActivity(intent)
        }
        return binding.root
    }
}