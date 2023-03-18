package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_home.R
import h2clt.fpt.quanlynhatro_h2clt_home.ThemKhuTroActivity
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.ActivityManHinhChinhChuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.DialogDanhSachKhuTroBinding

class ManHinhChinhChuTro : AppCompatActivity() {
    private lateinit var binding: ActivityManHinhChinhChuTroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManHinhChinhChuTroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgMenuManHinhChinh.setOnClickListener{
            val bottomSheetDialog =  BottomSheetDialog(this)
            val buil = DialogDanhSachKhuTroBinding.inflate(LayoutInflater.from(this))
            bottomSheetDialog.setContentView(buil.root)
            buil.icClose.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            buil.btnThemKhuTro.setOnClickListener {
                val intent = Intent(this,ThemKhuTroActivity::class.java)
                startActivity(intent)
            }


            bottomSheetDialog.show()
        }
        val adapter = ViewPager2ManHinhChinhAdapter(supportFragmentManager,lifecycle)
        binding.viewPager2ManHinhChinh.adapter = adapter
        TabLayoutMediator(binding.tabLayoutManHinhChinh,binding.viewPager2ManHinhChinh) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setIcon(R.drawable.home_icon)
                    tab.text = "Trang chủ"
                }
                1 -> {
                    tab.setIcon(R.drawable.dangtin_icon)
                    tab.text = "Đăng tin"
                }
                2 -> {
                    tab.setIcon(R.drawable.thongbao)
                    tab.text = "Thông báo"
                }
                3 -> {
                    tab.setIcon(R.drawable.canhan_icon)
                    tab.text = "Cá nhân"
                }
                else -> tab.text = "Trang chủ"
            }
        }.attach()



    }
}