package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ThongBaoAdapter

import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.ThongBaoDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentThongbaoBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.ThongBao

class FragmentThongBao:Fragment() {
    private lateinit var binding: FragmentThongbaoBinding
    lateinit var listThongBao:List<ThongBao>
    private var maKhu=""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThongbaoBinding.inflate(inflater,container,false)
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu=srf.getString(MA_KHU_KEY, "")!!
        listThongBao= ThongBaoDao(binding.root.context).getAllInThongBaoByMaKhu(maKhu)
        val adapter= ThongBaoAdapter(listThongBao)
        binding.rcv.adapter=adapter
        binding.rcv.layoutManager= LinearLayoutManager(context)
        return binding.root
    }
}