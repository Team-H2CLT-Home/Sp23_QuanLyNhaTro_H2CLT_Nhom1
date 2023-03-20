package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDangNhapBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.AnimotionLoadingBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro


const val THONG_TIN_DANG_NHAP="Thon_tin_dang_nhap"
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

                     Loading(this).show()
                       val hander = Handler()
                        hander.postDelayed(Runnable {
                            val intent = Intent(this@ActivityDangNhap, ActivityManHinhChinhChuTro::class.java)
                            startActivity(intent)
                        },3000)
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