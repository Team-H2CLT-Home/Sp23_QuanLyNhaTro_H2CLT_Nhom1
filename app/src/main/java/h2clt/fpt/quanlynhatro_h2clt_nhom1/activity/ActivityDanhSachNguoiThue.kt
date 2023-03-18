package h2clt.fpt.quanlynhatro_h2clt_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDanhSachNguoiThueBinding

class ActivityDanhSachNguoiThue : AppCompatActivity() {
    private lateinit var binding:ActivityDanhSachNguoiThueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachNguoiThueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewpagerDanhSachNguoiThueAdapter(supportFragmentManager, lifecycle)
        binding.viewpagerDanhSachNguoiThue.adapter = adapter
        TabLayoutMediator(binding.tabDanhSachNguoiThue, binding.viewpagerDanhSachNguoiThue) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Đang ở"
                }
                1 -> {
                    tab.text = "Đã ở"
                }
                else -> tab.text = "Đang ở"
            }
        }.attach()
    }
}