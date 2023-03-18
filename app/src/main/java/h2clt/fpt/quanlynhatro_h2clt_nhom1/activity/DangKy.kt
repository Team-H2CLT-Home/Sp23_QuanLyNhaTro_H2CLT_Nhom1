package h2clt.fpt.quanlynhatro_h2clt_home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_home.dao.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityDangKyBinding
import h2clt.fpt.quanlynhatro_h2clt_home.model.Admin

class DangKy : AppCompatActivity() {
    private lateinit var binding: ActivityDangKyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangKyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDangKy
        setSupportActionBar(binding.tbDangKy )
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        binding.btnLuuDK.setOnClickListener {
             if (validate()<1){
                 Snackbar.make(it,"Dữ liệu không được để trống",Toast.LENGTH_SHORT).show()
                 return@setOnClickListener
             }else{
                 val admin = Admin (
                     sdt = binding.edSoDienThoai.text.toString(),
                     ten_dang_nhap = binding.edTenDangNhapDangKy.text.toString(),
                     ho_ten = binding.edHoVaTen.text.toString(),
                     mat_khau = binding.edMatKhauDangKy.text.toString()
                 )
                 val dao = AdminDao(this@DangKy).insertAdmin(admin)
                 if (dao>0){
                     Snackbar.make(it,"Lưu thành công",Toast.LENGTH_SHORT).show()
                     xoaTrang()
                 }else{
                     Snackbar.make(it,"Lưu không thành công",Toast.LENGTH_SHORT).show()
                 }
             }
        }
        binding.btnHuyDK.setOnClickListener {
            xoaTrang()
        }

    }
    fun chuyenActivity(){
        val intent = Intent(this@DangKy, DangNhap::class.java)
        startActivity(intent)
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }

    fun validate(): Int {
        var check = -1
        if (binding.edHoVaTen.text.toString().isNotBlank() &&
            binding.edSoDienThoai.text.toString().isNotBlank() &&
            binding.edTenDangNhapDangKy.text.toString().isNotBlank() &&
                binding.edMatKhauDangKy.text.toString().isNotBlank()) {
            check = 1
        }
        return check
    }

    fun xoaTrang(){
        binding.edHoVaTen.setText("")
        binding.edMatKhauDangKy.setText("")
        binding.edSoDienThoai.setText("")
        binding.edTenDangNhapDangKy.setText("")
    }
}