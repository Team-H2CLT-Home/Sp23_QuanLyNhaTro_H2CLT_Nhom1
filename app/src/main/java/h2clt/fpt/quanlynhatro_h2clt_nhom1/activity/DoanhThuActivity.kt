package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDoanhThuBinding


class DoanhThuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoanhThuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoanhThuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.CardViewchonNgayDoanhThu.setOnClickListener {
            Snackbar.make(it,"Heloo Ông chú",Snackbar.LENGTH_SHORT).show()
            //kkkkk

        }
    }
}