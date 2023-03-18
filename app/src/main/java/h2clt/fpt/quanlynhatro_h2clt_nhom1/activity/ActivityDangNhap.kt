package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDangNhapBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro



const val USERNAME_KEY = "USERNAME"
const val PASSWORD_KEY = "PASSWORD"
const val CHECKBOX_KEY = "REMEMBER"
class ActivityDangNhap : AppCompatActivity() {
    private lateinit var binding: ActivityDangNhapBinding
    var listKhuTro = mutableListOf<KhuTro>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangNhapBinding.inflate(layoutInflater)
        val adminDao = AdminDao(this)
        listKhuTro = KhuTroDao(context = this@ActivityDangNhap).getAllInKhuTro() as MutableList<KhuTro>
        setContentView(binding.root)
        val pref : SharedPreferences = getSharedPreferences("My_file", MODE_PRIVATE)
        binding.edTenDangNhap.setText(pref.getString(USERNAME_KEY,""))
        binding.edMatKhau.setText(pref.getString(PASSWORD_KEY,""))
        binding.edCheckBox.isChecked= pref.getBoolean(CHECKBOX_KEY,false)
        binding.btnLuuDN.setOnClickListener {
            val userName = binding.edTenDangNhap.text.toString()
            val passWord = binding.edMatKhau.text.toString()
            val check = binding.edCheckBox
            if (userName.isNotBlank()&& passWord.isNotBlank()){
                if (adminDao.checkLogin(userName,passWord)){
                    if (listKhuTro.size > 0){
                        val intent = Intent(this@ActivityDangNhap, ActivityManHinhChinhChuTro::class.java)
                        startActivity(intent)
                        rememberUser(userName,passWord,check.isChecked)
                    }else{
                        val intent = Intent(this@ActivityDangNhap, ActivityHuongDanTaoKhu::class.java)
                        startActivity(intent)
                    }
                }else{
                    thongBaoLoi("Nhập sai dữ liệu")
                }
            }
            else{
                thongBaoLoi("Nhập đầy đủ thông tin")
            }

        }
        binding.btnHuyDN.setOnClickListener {
            binding.edTenDangNhap.setText("")
            binding.edMatKhau.setText("")
            binding.edCheckBox.isChecked = false
        }
        binding.linerDk.setOnClickListener {
            val intent = Intent(this@ActivityDangNhap,ActivityDangKy::class.java)
            startActivity(intent)
        }
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
    fun rememberUser(u : String , p : String, status : Boolean){
        val pref : SharedPreferences = getSharedPreferences("My_file", MODE_PRIVATE)
        val edit : SharedPreferences.Editor = pref.edit()
        if (!status){
            edit.clear()
        }else{
            edit.putString(USERNAME_KEY,u)
            edit.putString(PASSWORD_KEY,p)
            edit.putBoolean(CHECKBOX_KEY,status)
        }
        edit.commit()
    }
}