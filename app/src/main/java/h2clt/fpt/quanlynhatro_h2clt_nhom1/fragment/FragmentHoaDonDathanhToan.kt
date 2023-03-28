package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.Ma_PHONG_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHoaDonDathanhToanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon

class FragmentHoaDonDathanhToan : Fragment() {
    private lateinit var binding: FragmentHoaDonDathanhToanBinding
    private var maKhu = ""
    private var list = listOf<HoaDon>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoaDonDathanhToanBinding.inflate(LayoutInflater.from(context))
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "")!!
        list = HoaDonDao(requireContext()).getAllInHoaDonByMaKhu(maKhu)
//        53735b35-08d8-464d-8bab-58c8f2ab2e23
//        list = activity?.let { HoaDonDao(it).getAllInHoaDonByMaKhu(maKhu) }!!
       onResume()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hoaDonDaThanhToanAdapter  = HoaDonDaThanhToanAdapter(list)
        binding.rcyHoaDonDaThanhToan.adapter = hoaDonDaThanhToanAdapter
        hoaDonDaThanhToanAdapter.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()
        reload()
    }
    private fun reload(){
         list = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu)
        val hoaDonDaThanhToanAdapter  = HoaDonDaThanhToanAdapter(list)
//        val listND = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu)
//        val nguoiThueAdapter = NguoiThueAdapter(listND)
        binding.rcyHoaDonDaThanhToan.adapter = hoaDonDaThanhToanAdapter
        hoaDonDaThanhToanAdapter.notifyDataSetChanged()
    }

}