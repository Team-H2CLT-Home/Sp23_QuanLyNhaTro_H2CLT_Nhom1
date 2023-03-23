package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongDangThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongTrongBinding

class ActivityPhongDangThue : AppCompatActivity() {
    private lateinit var binding: ActivityPhongDangThueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhongDangThueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbPhongDangThue
        setSupportActionBar(binding.tbPhongDangThue)
        val ab = supportActionBar
        if (ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
    }


    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}