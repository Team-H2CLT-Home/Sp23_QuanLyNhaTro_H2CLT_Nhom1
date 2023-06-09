package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongDaCocGiuChoBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityTaoHopDongBinding

class ActivityPhongDaCocGiuCho : AppCompatActivity() {

    private lateinit var binding: ActivityPhongDaCocGiuChoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhongDaCocGiuChoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachPhong
        setSupportActionBar(binding.tbDanhSachPhong)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
    }
    fun chuyenActivity(){
        val intent = Intent(this@ActivityPhongDaCocGiuCho, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id: Int = item.getItemId();
        if (id == android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }


}