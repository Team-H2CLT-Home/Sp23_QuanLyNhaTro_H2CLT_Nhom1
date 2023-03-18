package h2clt.fpt.quanlynhatro_h2clt_home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ManHinhChinhChuTro
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDangNhapBinding

class DangNhap : AppCompatActivity() {
    private lateinit var binding: ActivityDangNhapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangNhapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLuuDN.setOnClickListener {
            if (binding.edTenDangNhap.text.toString().isNotBlank()||binding.edMatKhau.text.toString().isNotBlank()){
                val intent = Intent(this@DangNhap,ManHinhChinhChuTro::class.java)
                startActivity(intent)
            }else{
                Snackbar.make(it,"Không để trống",Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.btnHuyDN.setOnClickListener {
            binding.edTenDangNhap.setText("")
            binding.edMatKhau.setText("")
            binding.edCheckBox.isChecked = false
        }
        binding.linerDk.setOnClickListener {
            val intent = Intent(this@DangNhap,DangKy::class.java)
            startActivity(intent)
        }
    }
}