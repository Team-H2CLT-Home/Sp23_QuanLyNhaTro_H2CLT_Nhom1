package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.FragmentThongbaoBinding

class FragmentThongBao:Fragment() {
    private lateinit var binding: FragmentThongbaoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThongbaoBinding.inflate(inflater,container,false)
        val adapter = ViewPager2ThongBaoAdapter(parentFragmentManager,lifecycle)
        binding.viewPager2ThongBao.adapter = adapter
        TabLayoutMediator(binding.tabLayoutThongBao,binding.viewPager2ThongBao) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Tất cả"
                }
                1 -> {
                    tab.text = "Tiền"
                }
                2 -> {
                    tab.text = "Khác"
                }

                else -> tab.text = "Tất cả"
            }
        }.attach()



        return binding.root
    }
}