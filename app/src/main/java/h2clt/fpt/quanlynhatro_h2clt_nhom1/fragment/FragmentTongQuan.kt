package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.TEN_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.list
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.TablayoutTongquanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FragmentTongQuan:Fragment() {
    private lateinit var binding: TablayoutTongquanBinding
    var listHopDongSapHetHan = listOf<HopDong>()
    var listHopDong= listOf<HopDong>()
    var listHoaDon = listOf<HoaDon>()
    var listPhong = listOf<Phong>()
    var listPhongTrong = listOf<Phong>()
    var listPhongDangThue = listOf<Phong>()
    private var maKhu=""
    private var tenKhu=""
    private var listKhuTro = listOf<KhuTro>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TablayoutTongquanBinding.inflate(layoutInflater)


        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        //================================
        val admin =
            binding.root.context.getSharedPreferences(THONG_TIN_DANG_NHAP, AppCompatActivity.MODE_PRIVATE).getString(USERNAME_KEY, "")!!
        listKhuTro = KhuTroDao(binding.root.context).getAllInKhuTroByAdmin(admin)

        val pre = binding.root.context.getSharedPreferences(FILE_NAME, AppCompatActivity.MODE_PRIVATE)

        val khuTro = listKhuTro.find { it.ma_khu_tro == maKhu }
        binding.tvTenKhuTongQuan.text = ("Khu ") + khuTro?.ten_khu_tro
        pre.edit().putString(MA_KHU_KEY, maKhu).commit()
        onResume()
        binding.phongTrong.setOnClickListener {
            val intent = Intent(context,ActivityPhongTrong::class.java)
            startActivity(intent)
        }
        binding.phongDangChoThue.setOnClickListener {
            val intent = Intent(context,ActivityPhongDangThue::class.java)
            startActivity(intent)
        }
        binding.phongSapHetHan.setOnClickListener {

            val intent = Intent(context, ActivityPhongSapHetHopDong::class.java)
            startActivity(intent)
        }

        val sdf = SimpleDateFormat("yyyy-MM")
        val ngay = sdf.format(Date())
        listHoaDon = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu).filter { it.trang_thai_hoa_don == 0 && ngay in it.ngay_tao_hoa_don }
        binding.slPhongChuaDongTien.setText(""+listHoaDon.size)
        binding.phongChuaDongTien.setOnClickListener {
            val intent = Intent(context,ActivityPhongChuaDongTien::class.java)
            startActivity(intent)
        }

        binding.doanhThu.setOnClickListener {
            val intent = Intent(context,ActivityDoanhThu::class.java)
            startActivity(intent)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        listPhong = PhongDao(binding.root.context).getAllInPhongByMaKhu(maKhu)
        listPhongDangThue=PhongDao(binding.root.context).getAllInPhongByMaKhu(maKhu).filter { it.trang_thai_phong==1 }
        listPhongTrong=PhongDao(binding.root.context).getAllInPhongByMaKhu(maKhu).filter { it.trang_thai_phong==0 }
        //updateHopDong()
        listHopDongSapHetHan = HopDongDao(binding.root.context).getHopDongSapHetHanByMaKhu(maKhu,2,1)
        binding.tvPhongSapHetHanHD.setText(""+listHopDongSapHetHan.size)
        binding.tvSoPhongTongQuan.setText(""+listPhong.size)
        binding.tvPhongTrong.setText(""+listPhongTrong.size)
        binding.tvPhongDangThue.setText(""+listPhongDangThue.size)
    }
}