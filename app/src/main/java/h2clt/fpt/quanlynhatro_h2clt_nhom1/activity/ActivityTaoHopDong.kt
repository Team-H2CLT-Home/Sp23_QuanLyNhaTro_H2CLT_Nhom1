package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.PhongInteface
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.PhongTrotaoHopDongAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityTaoHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class ActivityTaoHopDong : AppCompatActivity() {
    private lateinit var binding:ActivityTaoHopDongBinding
    var listMaPhong = listOf<String>()
    var listPhongChuaCoHopDong = mutableListOf<Phong>()
    var listPhong = listOf<Phong>()
    private var maKhu=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaoHopDongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbDanhSachPhong)
        val ab = supportActionBar
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        //=======================================
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        onResume()
    }

    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }

    override fun onResume() {
        super.onResume()
        listPhongChuaCoHopDong = PhongDao(this@ActivityTaoHopDong).getPhongChuaCoHopDong(maKhu) as MutableList<Phong>
        val adapter = PhongTrotaoHopDongAdapter(listPhongChuaCoHopDong, object : PhongInteface{
            override fun OnCLickPhong(pos: Int) {
                val intent = Intent(this@ActivityTaoHopDong,ActivitytaoHopDongMoi::class.java)
                val bundle = Bundle()
                bundle.putString("tenPhong",listPhongChuaCoHopDong[pos].ten_phong)
                bundle.putString("maPhong",listPhongChuaCoHopDong[pos].ma_phong)
                intent.putExtras(bundle)
                startActivity(intent)
            }

        })
        binding.rcyPhongTrongCanTaoHopDong.layoutManager = LinearLayoutManager(this)
        binding.rcyPhongTrongCanTaoHopDong.adapter = adapter
    }
}