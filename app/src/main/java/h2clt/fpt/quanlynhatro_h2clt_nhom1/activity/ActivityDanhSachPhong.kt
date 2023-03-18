package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDanhSachPhongBinding

class ActivityDanhSachPhong : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachPhongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewpagerDanhSachPhongAdapter(supportFragmentManager, lifecycle)
        binding.viewpagerDanhSachPhong.adapter = adapter
        TabLayoutMediator(binding.tabDanhSachPhong, binding.viewpagerDanhSachPhong) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Tất cả phòng"
                }
                1 -> {
                    tab.text = "Phòng trống"
                }
                else -> tab.text = "Phòng đã ở"
            }
        }.attach()
    }
}