package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDanhSachHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDanhSachPhongBinding

class ActivityDanhSachHoaDon : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewpagerDanhSachHoaDonAdapter(supportFragmentManager, lifecycle)
        binding.viewpagerHoaDon.adapter = adapter
        TabLayoutMediator(binding.tabDanhSachHoaDon, binding.viewpagerHoaDon) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Đã thanh toán"
                }
                1 -> {
                    tab.text = "Chưa thanh toán"
                }
                else -> tab.text = "Đã thanh toán"
            }
        }.attach()
    }
}