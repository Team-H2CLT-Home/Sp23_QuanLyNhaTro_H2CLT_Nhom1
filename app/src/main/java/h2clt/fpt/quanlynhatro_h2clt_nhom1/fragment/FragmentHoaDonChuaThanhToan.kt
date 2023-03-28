package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHoaDonChuaThanhToanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon


class FragmentHoaDonChuaThanhToan : Fragment() {
    private lateinit var binding : FragmentHoaDonChuaThanhToanBinding
    private var maKhu = ""
    private var list = listOf<HoaDon>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoaDonChuaThanhToanBinding.inflate(layoutInflater)
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "")!!
        list = activity?.let { HoaDonDao(it).getAllInHoaDonByMaKhu(maKhu) }!!
        onResume()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hoaDonDaThanhToanAdapter  = HoaDonDaThanhToanAdapter(list)
        binding.rcyHoaDonChuaThanhToan.adapter = hoaDonDaThanhToanAdapter
        hoaDonDaThanhToanAdapter.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()
        reload()
    }
    private fun reload(){
        list = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu)
        val hoaDonAdapter  = HoaDonAdapter(list)
        val hd = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu).filter { it.trang_thai_hoa_don == 0 }

        val listND = NguoiDungDao(binding.root.context).getAllInNguoiDungByMaKhu(maKhu)
        val nguoiThueAdapter = NguoiThueAdapter(listND)
        binding.rcyHoaDonChuaThanhToan.adapter = hoaDonAdapter
        hoaDonAdapter.notifyDataSetChanged()
       // Toast.makeText(binding.root.context,""+hd, Toast.LENGTH_LONG).show()
        Log.d("chech", "reload: "+hd.size)
    }
}