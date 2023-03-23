package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachHopDongBinding

class ActivityDanhSachHopDong : AppCompatActivity() {
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
        if (ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
           finish();
        return super.onOptionsItemSelected(item);
    }
}