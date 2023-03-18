package h2clt.fpt.quanlynhatro_h2clt_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.FragmentHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.TablayoutQuanlyBinding

class FragmentHoaDon : Fragment() {

    private lateinit var binding: FragmentHoaDonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHoaDonBinding.inflate(inflater,container,false)
//        val adapter =
//            activity?.let { ViewpagerDanhSachHoaDonAdapter(it.supportFragmentManager, lifecycle) }
//        binding.viewpagerHoaDon.adapter = adapter
//        TabLayoutMediator(binding.tabDanhSachHoaDon, binding.viewpagerHoaDon) { tab, pos ->
//            when (pos) {
//                0 -> {
//                    tab.text = "Đã thanh toán"
//                }
//                1 -> {
//                    tab.text = "Chưa thanh toán"
//                }
//                else -> tab.text = "Đã thanh toán"
//            }
//        }.attach()
        return binding.root
    }
}