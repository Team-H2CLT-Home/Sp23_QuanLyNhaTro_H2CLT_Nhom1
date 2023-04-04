package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.PhongTroAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongDangThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongTrongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class ActivityPhongDangThue : AppCompatActivity() {
    private lateinit var binding: ActivityPhongDangThueBinding
    var listPhongDangThue= listOf<Phong>()
    var maKhu=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhongDangThueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbPhongDangThue
        setSupportActionBar(binding.tbPhongDangThue)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)

        val srf=this?.getSharedPreferences(FILE_NAME, AppCompatActivity.MODE_PRIVATE)
        maKhu=srf?.getString(MA_KHU_KEY, "")!!
        reload()
    }

    fun chuyenActivity(){
        val intent = Intent(this@ActivityPhongDangThue, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }

    private fun reload(){
        val phongDao= this?.let { PhongDao(it) }!!
        listPhongDangThue=phongDao.getAllInPhongByMaKhu(maKhu).filter { it.trang_thai_phong==1 }
        val phongTroAdapter= PhongTroAdapter(listPhongDangThue)
        binding.recyclerDanhSachPhongDangThue.adapter=phongTroAdapter
        binding.recyclerDanhSachPhongDangThue.layoutManager= LinearLayoutManager(this)
    }
}