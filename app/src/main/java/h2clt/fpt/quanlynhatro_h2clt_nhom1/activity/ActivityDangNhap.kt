package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDangNhapBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import java.util.logging.Handler


const val THONG_TIN_DANG_NHAP="Thon_tin_dang_nhap"
const val USERNAME_KEY = "USERNAME"
const val PASSWORD_KEY = "PASSWORD"
const val CHECKBOX_KEY = "REMEMBER"
class ActivityDangNhap : AppCompatActivity() {
    private lateinit var binding: ActivityDangNhapBinding
    private var listKhuTro = listOf<KhuTro>()

    private var admin=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDangNhapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adminDao=AdminDao(applicationContext)
        val khuTroDao=KhuTroDao(applicationContext)
        val pref : SharedPreferences = getSharedPreferences(THONG_TIN_DANG_NHAP, MODE_PRIVATE)
        admin =pref.getString(USERNAME_KEY,"")!!
        binding.edTenDangNhap.setText(pref.getString(USERNAME_KEY,""))
        if (pref.getBoolean(CHECKBOX_KEY, false)) {
            binding.edMatKhau.setText(pref.getString(PASSWORD_KEY, ""))
            binding.edCheckBox.isChecked = pref.getBoolean(CHECKBOX_KEY, false)
        }
        else{
            binding.edMatKhau.setText("")
            binding.edCheckBox.isChecked =false
        }
        //tự động đăng nhập không cần ấn

        binding.btnLuuDN.setOnClickListener {
            val userName = binding.edTenDangNhap.text.toString()
            val passWord = binding.edMatKhau.text.toString()
            val check = binding.edCheckBox
            if(admin!=null) admin=userName
            listKhuTro=khuTroDao.getAllInKhuTroByAdmin(admin)
            if (userName.isNotBlank()&& passWord.isNotBlank()){
                if (adminDao.checkLogin(userName,passWord)){
                    if (listKhuTro.isNotEmpty()){
                        Loading(this).show()
                        val handler = android.os.Handler()
                        handler.postDelayed(Runnable {
                            val intent = Intent(this@ActivityDangNhap, ActivityManHinhChinhChuTro::class.java)
                            startActivity(intent)
                            finish()
                            Loading(this).cancel()
                        },2000)
                        rememberUser(userName,passWord,check.isChecked)

                    }else{
                        val intent = Intent(this@ActivityDangNhap, ActivityHuongDanTaoKhu::class.java)
                        startActivity(intent)
                        finish()
                        rememberUser(userName,passWord,check.isChecked)

                    }
                }else{
                    thongBaoLoi("Tên tài khoản hoặc mật khẩu không đúng")
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
            finish()

        }
    }
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Lỗi đăng nhập")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun rememberUser(u : String , p : String, status : Boolean){
        val pref : SharedPreferences = getSharedPreferences(THONG_TIN_DANG_NHAP, MODE_PRIVATE)
        val edit  = pref.edit()
        edit.putString(USERNAME_KEY,u)
        edit.putString(PASSWORD_KEY,p)
        edit.putBoolean(CHECKBOX_KEY,status)
        edit.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        Loading(this@ActivityDangNhap).cancel()
    }

}