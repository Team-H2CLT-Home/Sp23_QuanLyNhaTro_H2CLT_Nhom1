package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityChiTietPhongBinding

class ChiTietPhongActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChiTietPhongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChiTietPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewPagerChiTietPhongAdapter(supportFragmentManager,lifecycle)
        binding.viewPager2ChiTietPhong.adapter = adapter
        TabLayoutMediator(binding.tabLayoutChiTietPhong,binding.viewPager2ChiTietPhong){tab,pos ->
            when(pos){
                0->{
                    tab.setIcon(R.drawable.home_icon)
                    tab.text = "Thông tin"
                }
                else -> tab.text = "Hóa đơn"
            }

        }.attach()
//        val adapter = ViewpagerDanhSachPhongAdapter(supportFragmentManager, lifecycle)
//        binding.viewpagerDanhSachPhong.adapter = adapter
//        TabLayoutMediator(binding.tabDanhSachPhong, binding.viewpagerDanhSachPhong) { tab, pos ->
//            when (pos) {
//                0 -> {
//                    tab.text = "Tất cả phòng"
//                }
//                1 -> {
//                    tab.text = "Phòng trống"
//                }
//                else -> tab.text = "Phòng đã ở"
//            }
//        }.attach()
    }
}