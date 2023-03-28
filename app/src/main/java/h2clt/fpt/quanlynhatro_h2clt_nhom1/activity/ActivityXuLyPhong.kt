package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HopDongPhongSapHetHanAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongDaCocGiuChoBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityTaoHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityXuLyPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong

class ActivityXuLyPhong : AppCompatActivity() {
    private lateinit var binding: ActivityXuLyPhongBinding
    var listHopDongSapHetHan = listOf<HopDong>()
    private var maKhu=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityXuLyPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbXuLyPhong)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        //==================================

        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        listHopDongSapHetHan = HopDongDao(this@ActivityXuLyPhong).getHopDongSapHetHanByMaKhu(maKhu,0)
        //listHopDong=HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDong()
        val hopDongAdapter= HopDongPhongSapHetHanAdapter(listHopDongSapHetHan)
        binding.recyclerXuLyPhong.adapter = hopDongAdapter
        binding.recyclerXuLyPhong.layoutManager= LinearLayoutManager(this)
    }

    fun chuyenActivity(){
        val intent = Intent(this@ActivityXuLyPhong, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
}