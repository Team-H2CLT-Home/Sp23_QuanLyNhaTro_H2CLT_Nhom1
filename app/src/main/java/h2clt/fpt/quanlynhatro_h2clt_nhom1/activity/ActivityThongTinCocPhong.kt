package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinCocPhongBinding

class ActivityThongTinCocPhong : AppCompatActivity() {
    private lateinit var binding:ActivityThongTinCocPhongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinCocPhongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBoCocGiuCho.setOnClickListener {
            val intent = Intent(this@ActivityThongTinCocPhong,ActivityDatCocGiuCho::class.java)
            startActivity(intent)
            finish()
        }
    }
}