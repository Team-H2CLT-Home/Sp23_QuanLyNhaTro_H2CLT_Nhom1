package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachPhongChuaTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityXuLyPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding

class ActivityDanhSachPhongChuaTaoHoaDon : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachPhongChuaTaoHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachPhongChuaTaoHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachPhong
        setSupportActionBar(binding.tbDanhSachPhong)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    fun chuyenActivity(){
        val intent = Intent(this@ActivityDanhSachPhongChuaTaoHoaDon, ActivityManHinhChinhChuTro::class.java)
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