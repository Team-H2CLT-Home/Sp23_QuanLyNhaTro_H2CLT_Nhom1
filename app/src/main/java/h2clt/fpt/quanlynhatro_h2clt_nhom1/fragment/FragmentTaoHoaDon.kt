package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityManHinhChinhChuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding


class FragmentTaoHoaDon : AppCompatActivity() {
    private lateinit var binding: FragmentTaoHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTaoHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listViewTaoHoaDon.adapter

        binding.tbTaoHoaDon
        setSupportActionBar(binding.tbTaoHoaDon)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    fun chuyenActivity(){
        val intent = Intent(this@FragmentTaoHoaDon, ActivityManHinhChinhChuTro::class.java)
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