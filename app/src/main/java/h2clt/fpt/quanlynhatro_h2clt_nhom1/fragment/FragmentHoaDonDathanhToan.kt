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
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHoaDonDathanhToanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon

class FragmentHoaDonDathanhToan : Fragment() {
    private lateinit var binding: FragmentHoaDonDathanhToanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoaDonDathanhToanBinding.inflate(layoutInflater)



        val list = HoaDonDao(binding.root.context).getAllInHoaDon()

        val hoaDonDaThanhToanAdapter  = HoaDonDaThanhToanAdapter(list)
        binding.rcyHoaDonDaThanhToan.adapter = hoaDonDaThanhToanAdapter
        hoaDonDaThanhToanAdapter.notifyDataSetChanged()


        return binding.root
    }

}