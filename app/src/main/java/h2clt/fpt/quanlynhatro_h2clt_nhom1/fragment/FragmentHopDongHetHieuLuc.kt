package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HopDongAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHopDongConHieuLucBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHopDongHetHieuLucBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.SimpleDateFormat

class FragmentHopDongHetHieuLuc : Fragment() {
    private lateinit var binding: FragmentHopDongHetHieuLucBinding
    var listHopDongHetHieuLuc = listOf<HopDong>()
    private var maKhu=""
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHopDongHetHieuLucBinding.inflate(LayoutInflater.from(context))
        reloadDS()
        return binding.root
    }

    private fun reloadDS() {
        val srf = this.binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        listHopDongHetHieuLuc = HopDongDao(requireContext()).getAllInHopDongByMaKhu(maKhu,0)
        //listHopDong=HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDong()
        val hopDongAdapter = HopDongAdapter(listHopDongHetHieuLuc)
        this.binding.rcyPhongHetHieuLuc.adapter = hopDongAdapter
        this.binding.rcyPhongHetHieuLuc.layoutManager = LinearLayoutManager(requireContext())
    }

}