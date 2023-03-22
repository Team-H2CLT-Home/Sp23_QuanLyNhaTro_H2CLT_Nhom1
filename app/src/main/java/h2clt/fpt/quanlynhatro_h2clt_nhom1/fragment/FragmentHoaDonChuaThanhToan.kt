package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HoaDonAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HoaDonDaThanhToanAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHoaDonChuaThanhToanBinding


class FragmentHoaDonChuaThanhToan : Fragment() {
    private lateinit var binding : FragmentHoaDonChuaThanhToanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoaDonChuaThanhToanBinding.inflate(layoutInflater)



        val list = HoaDonDao(binding.root.context).getAllInHoaDon()

        val hoaDonAdapter  = HoaDonAdapter(list)
        binding.rcyHoaDonChuaThanhToan.adapter = hoaDonAdapter
        hoaDonAdapter.notifyDataSetChanged()



        // Inflate the layout for this fragment
        return binding.root
    }
}