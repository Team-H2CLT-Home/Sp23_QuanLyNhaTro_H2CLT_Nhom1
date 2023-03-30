package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityTaoPhongKhiThemKhuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.util.UUID

class ActivityTaoPhongKhiThemKhu : AppCompatActivity() {
    private lateinit var binding:ActivityTaoPhongKhiThemKhuBinding
    private val listDichVuDien= listOf<Triple<String, Int,Int>>(
        Triple("Miễn phí/Không sử dung",0, 0), Triple("Tính theo đầu người",2,70_000), Triple("Tính theo phòng",3, 150_000),
       Triple("Tính theo đồng hồ(Phổ biến)", 1, 3000, )
    )
    private val listDichVuNuoc= listOf<Triple<String, Int,Int>>(
        Triple("Miễn phí/Không sử dung",0, 0), Triple("Tính theo đầu người",2,70_000), Triple("Tính theo phòng",3, 100_000),
        Triple("Tính theo đồng hồ(Phổ biến)", 1 ,20_000, )
    )
    private val listDichVuRac= listOf<Triple<String, Int,Int>>(
        Triple("Miễn phí/Không sử dung",0, 0), Triple("Tính theo đầu người",2, 20_000), Triple("Tính theo phòng",3, 120_000)
    )
    private val listDichVuMang= listOf<Triple<String, Int,Int>>(
        Triple("Miễn phí/Không sử dung",0, 0), Triple("Tính theo đầu người",2, 50_000), Triple("Tính theo phòng",3, 100_000)
    )
    private var trangThaiDichVuDien=1
    private var trangThaiDichVuNuoc=1
    private var trangThaiDichVuRac=0
    private var trangThaiDichVuMang=0

    private var giaDien=3000
    private var giaNuoc=20000
    private var giaRac=0
    private var giaMang=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaoPhongKhiThemKhuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val srf=getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val phongDao=PhongDao(this)
        val loaiDichVuPhongDao=LoaiDichVuPhongDao(this)
        val soLuongPhong=intent.getIntExtra(SO_LUONG_PHONG_KEY, 0)
        val maKhu=intent.getStringExtra(MA_KHU_TU_TAO_KHU)!!
        binding.edSoPhongTro.isEnabled=false
        binding.edSoPhongTro.setTextColor(Color.BLACK)
        binding.edSoPhongTro.setText(soLuongPhong.toString())

        binding.lnDichVuDien.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listText=listDichVuDien.map { it.first }
            val arrayAdapter=ArrayAdapter(this, R.layout.layout_item_chon_dich_vu, listText)
            dialog.listLoaiDichVu.adapter=arrayAdapter
            dialog.tvDichVuPhong.text="Dịch vụ điên"
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->
                binding.tvDichVuDien.text=listDichVuDien[i].first
                trangThaiDichVuDien=listDichVuDien[i].second
                giaDien=listDichVuDien[i].third
                build.dismiss()
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
            build.setView(dialog.root)
            build.show()
        }
        binding.lnDichVuNuoc.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listText=listDichVuNuoc.map { it.first }
            val arrayAdapter=ArrayAdapter(this, R.layout.layout_item_chon_dich_vu, listText)
            dialog.listLoaiDichVu.adapter=arrayAdapter
            dialog.tvDichVuPhong.text="Dịch vụ nước"
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->
                binding.tvDichVuNuoc.text=listDichVuNuoc[i].first
                trangThaiDichVuNuoc=listDichVuNuoc[i].second
                giaNuoc=listDichVuNuoc[i].third
                build.dismiss()
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
            build.setView(dialog.root)
            build.show()
        }

        binding.lnDichVuRac.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listText=listDichVuRac.map { it.first }
            val arrayAdapter=ArrayAdapter(this, R.layout.layout_item_chon_dich_vu, listText)
            dialog.listLoaiDichVu.adapter=arrayAdapter
            dialog.tvDichVuPhong.text="Dịch vụ rác"
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->
                binding.tvTienRac.text=listDichVuRac[i].first
                trangThaiDichVuRac=listDichVuRac[i].second
                giaRac=listDichVuRac[i].third
                build.dismiss()
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
            build.setView(dialog.root)
            build.show()
        }

        binding.lnDichVuMang.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listText=listDichVuMang.map { it.first }
            val arrayAdapter=ArrayAdapter(this, R.layout.layout_item_chon_dich_vu, listText)
            dialog.listLoaiDichVu.adapter=arrayAdapter
            dialog.tvDichVuPhong.text="Dịch vụ mạng/Internet"
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->
                binding.tvDichVuMang.text=listDichVuMang[i].first
                trangThaiDichVuMang=listDichVuMang[i].second
                giaMang=listDichVuMang[i].third
                build.dismiss()
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
            build.setView(dialog.root)
            build.show()
        }
        binding.btnLuuThemPhong.setOnClickListener {
            val tenPhong=binding.edTenPhongTro.text.toString()
            val soNguoiO=binding.edSoNguoiToiDa.text.toString().toInt()
            val giaThue=binding.edGiaThue.text.toString().toLong()
            val dienTich=binding.edDienTichPhong.text.toString().toInt()
            repeat(soLuongPhong){
                val idPhong=UUID.randomUUID().toString()
                val phong=Phong(
                    ma_phong = idPhong,
                    ma_khu = maKhu,
                    trang_thai_phong = 0,
                    so_nguoi_o = soNguoiO,
                    gia_thue =giaThue,
                    dien_tich = dienTich,
                    ten_phong ="$tenPhong ${it+1}"
                )
                val dien=LoaiDichVu(
                    ma_phong = idPhong,
                    trang_thai_loai_dich_vu = trangThaiDichVuDien,
                    ten_loai_dich_vu = "Tiền điện",
                    ma_loai_dich_vu = UUID.randomUUID().toString(),
                    gia_dich_vu = giaDien,
                    ma_khu_tro = maKhu,
                    so_moi = -1,
                    so_cu = -1
                )
                val nuoc=LoaiDichVu(
                    ma_phong = idPhong,
                    trang_thai_loai_dich_vu = trangThaiDichVuNuoc,
                    ten_loai_dich_vu = "Tiền nước",
                    ma_loai_dich_vu = UUID.randomUUID().toString(),
                    gia_dich_vu = giaNuoc,
                    ma_khu_tro = maKhu,
                    so_moi = -1,
                    so_cu = -1
                )
                val rac=LoaiDichVu(
                    ma_phong = idPhong,
                    trang_thai_loai_dich_vu = trangThaiDichVuRac,
                    ten_loai_dich_vu = "Tiền rác",
                    ma_loai_dich_vu = UUID.randomUUID().toString(),
                    gia_dich_vu = giaRac,
                    ma_khu_tro = maKhu,
                    so_moi = -1,
                    so_cu = -1
                )
                val mang=LoaiDichVu(
                    ma_phong = idPhong,
                    trang_thai_loai_dich_vu = trangThaiDichVuMang,
                    ten_loai_dich_vu = "Tiền mạng",
                    ma_loai_dich_vu = UUID.randomUUID().toString(),
                    gia_dich_vu = giaMang,
                    ma_khu_tro = maKhu,
                    so_moi = -1,
                    so_cu = -1
                )
                phongDao.insertPhong(phong)
                loaiDichVuPhongDao.insertLoaiDichVuPhong(dien)
                loaiDichVuPhongDao.insertLoaiDichVuPhong(nuoc)
                loaiDichVuPhongDao.insertLoaiDichVuPhong(rac)
                loaiDichVuPhongDao.insertLoaiDichVuPhong(mang)
            }
            val intent=Intent(this@ActivityTaoPhongKhiThemKhu, ActivityManHinhChinhChuTro::class.java)
            intent.putExtra(MA_KHU_KEY, maKhu)
            startActivity(intent)
            finishAffinity()
        }
    }


}