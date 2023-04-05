package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDangKyBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin


class ActivityDangKy : AppCompatActivity() {
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
                     stk = "",
                     ngan_hang = "",
                     ngay_sinh = binding.edNgaySinh.text.toString(),
                     mat_khau = binding.edMatKhauDangKy.text.toString()
                 )
                 val dao = AdminDao(this@ActivityDangKy).insertAdmin(admin)
                 if (dao>0){
                     thongBaoThanhCong("Bạn đã tạo thành công tài khoản mới !!!")
                     xoaTrang()


                 }else{
                    thongBaoLoi("bạn đã tạo không thành công tài khoản mới !!!")
                 }
             }
        }
        binding.btnHuyDK.setOnClickListener {
            xoaTrang()
        }
    }
    fun chuyenActivity(){
        val intent = Intent(this@ActivityDangKy, ActivityDangNhap::class.java)
        startActivity(intent)
        finish()
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
                binding.edMatKhauDangKy.text.toString().isNotBlank()
            &&
            binding.edNgaySinh.text.toString().isNotBlank()) {
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
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun thongBaoThanhCong(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@ActivityDangKy,ActivityDangNhap::class.java)
            startActivity(intent)
            finish()
        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }


}