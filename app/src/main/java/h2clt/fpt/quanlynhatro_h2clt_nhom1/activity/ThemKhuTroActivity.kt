package h2clt.fpt.quanlynhatro_h2clt_home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityThemKhuTroBinding

class ThemKhuTroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemKhuTroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemKhuTroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTiepTuc.setOnClickListener {
            Snackbar.make(it,"Thêm thành công",Snackbar.LENGTH_SHORT).show()
        }
    }
}