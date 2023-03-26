package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.KhuTroAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ViewPagerManHinhChinhAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityManHinhChinhChuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogDanhSachKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro


class ActivityManHinhChinhChuTro : AppCompatActivity() {
    private lateinit var binding: ActivityManHinhChinhChuTroBinding
    private var listKhuTro = listOf<KhuTro>()
    private var maKhu = ""
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManHinhChinhChuTroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomSheetDialog = BottomSheetDialog(this)
        val admin =
            getSharedPreferences(THONG_TIN_DANG_NHAP, MODE_PRIVATE).getString(USERNAME_KEY, "")!!
        listKhuTro = KhuTroDao(applicationContext).getAllInKhuTroByAdmin(admin)

        val pre = getSharedPreferences(FILE_NAME, MODE_PRIVATE)

        if (listKhuTro.isEmpty()) {
            val intent = Intent(this@ActivityManHinhChinhChuTro, ActivityHuongDanTaoKhu::class.java)
            startActivity(intent)
            finish()
        }
        when {
            intent.getStringExtra(MA_KHU_KEY) == null -> {
                if (listKhuTro.isNotEmpty())
                    maKhu = listKhuTro[0].ma_khu_tro
            }
            intent.getStringExtra(MA_KHU_KEY) != null -> {
                maKhu = intent.getStringExtra(MA_KHU_KEY)!!
            }
        }
        val khuTro = listKhuTro.find { it.ma_khu_tro == maKhu }
        binding.titleTenKhuTro.text = ("Khu ") + khuTro?.ten_khu_tro
        pre.edit().putString(MA_KHU_KEY, maKhu).commit()

        binding.imgMenuManHinhChinh.setOnClickListener {
            val buil = DialogDanhSachKhuTroBinding.inflate(LayoutInflater.from(this))
            val adapter = KhuTroAdapter(listKhuTro)
            buil.rcyKhuTro.layoutManager = LinearLayoutManager(applicationContext)
            buil.rcyKhuTro.adapter = adapter

            bottomSheetDialog.setContentView(buil.root)
            buil.icClose.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            buil.btnThemKhuTro.setOnClickListener {
                val intent = Intent(this, ActivityThemKhuTro::class.java)
                startActivity(intent)
                finish()
            }


            bottomSheetDialog.show()

            bottomSheetDialog.show()


        }
        val adapter = ViewPagerManHinhChinhAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2ManHinhChinh.adapter = adapter
        TabLayoutMediator(
            binding.tabLayoutManHinhChinh,
            binding.viewPager2ManHinhChinh
        ) { tab, pos ->
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


    override fun onPause() {
        super.onPause()
        bottomSheetDialog.dismiss()
        Log.d("TAG", "onPause: called")
    }

}

