package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCapNhatHopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityKetThucHopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityCapNhatHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHopDongConHieuLucBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHopDongDaHetHanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.SimpleDateFormat


class FragmentHopDongConHieuLuc : Fragment() {
    private lateinit var binding: FragmentHopDongConHieuLucBinding
    var listHopDongConHieuLuc = listOf<HopDong>()
    private var maKhu=""
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHopDongConHieuLucBinding.inflate(LayoutInflater.from(context))
        onResume()
        return binding.root
    }

    private fun reloadDS() {
        val srf = this.binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        listHopDongConHieuLuc = HopDongDao(requireContext()).getAllInHopDongByMaKhu(maKhu,1)
        //listHopDong=HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDong()
        val hopDongAdapter = HopDongConHieuLucAdapter(listHopDongConHieuLuc,object :HopDongInterface{
            override fun OnClickHopDong(pos: Int) {
                val hopDong = listHopDongConHieuLuc.get(pos)
                val intent = Intent(requireContext(), ActivityCapNhatHopDong::class.java)
                intent.putExtra("hopDong",hopDong)
                startActivity(intent)
                //Toast.makeText(requireContext(),"1",Toast.LENGTH_SHORT).show()
            }

        })
        this.binding.rcyPhongConHieuLuc.adapter = hopDongAdapter
        this.binding.rcyPhongConHieuLuc.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        reloadDS()
    }

}