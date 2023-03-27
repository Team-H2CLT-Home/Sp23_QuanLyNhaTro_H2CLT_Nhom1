package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R

import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ViewpagerDanhSachPhongAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong


class ActivityDanhSachPhong : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachPhongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachPhong
        setSupportActionBar(binding.tbDanhSachPhong)
        val ab = supportActionBar
        if (ab != null){
            ab.setHomeAsUpIndicator(R.drawable.black_left)
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        if (ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
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
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
}