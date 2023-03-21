package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ViewpagerDanhSachNguoiThueAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachNguoiThueBinding


class ActivityDanhSachNguoiThue : AppCompatActivity() {
    private lateinit var binding:ActivityDanhSachNguoiThueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachNguoiThueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabDSNguoiThue
        setSupportActionBar(binding.tabDSNguoiThue)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
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
    fun chuyenActivity(){
        val intent = Intent(this@ActivityDanhSachNguoiThue, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
}