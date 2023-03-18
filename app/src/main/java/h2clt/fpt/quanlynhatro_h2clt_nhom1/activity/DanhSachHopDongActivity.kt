package h2clt.fpt.quanlynhatro_h2clt_home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ManHinhChinhChuTro
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDanhSachHopDongBinding

class DanhSachHopDongActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachHopDongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachHopDongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolBerDSHopDong
        setSupportActionBar(binding.toolBerDSHopDong )
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
    }
    fun chuyenActivity(){
        val intent = Intent(this@DanhSachHopDongActivity,ManHinhChinhChuTro::class.java)
        startActivity(intent)
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
}