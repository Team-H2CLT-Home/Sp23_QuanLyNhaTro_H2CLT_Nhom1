package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachPhongChuaTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongTrongBinding

class ActivityPhongTrong : AppCompatActivity() {
    private lateinit var binding: ActivityPhongTrongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhongTrongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbPhongTrong
        setSupportActionBar(binding.tbPhongTrong)
        val ab = getSupportActionBar()
        if (ab != null){
            ab.setHomeAsUpIndicator(R.drawable.black_left)
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
    }

    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
}