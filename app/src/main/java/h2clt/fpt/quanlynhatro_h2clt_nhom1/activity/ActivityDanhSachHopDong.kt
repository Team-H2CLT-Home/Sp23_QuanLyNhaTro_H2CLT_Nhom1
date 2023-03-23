package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HopDongAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.NguoiThueAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong

class ActivityDanhSachHopDong : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachHopDongBinding
    var listHopDong = listOf<HopDong>()
    private var maKhu=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachHopDongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolBerDSHopDong
        setSupportActionBar(binding.toolBerDSHopDong )
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        listHopDong = HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDongByMaKhu(maKhu)
        //listHopDong=HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDong()
        val hopDongAdapter= HopDongAdapter(listHopDong)
        binding.recyclerDanhSachHopDong.adapter=hopDongAdapter
        binding.recyclerDanhSachHopDong.layoutManager= LinearLayoutManager(this)
    }

    fun chuyenActivity(){
        val intent = Intent(this@ActivityDanhSachHopDong, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }

}