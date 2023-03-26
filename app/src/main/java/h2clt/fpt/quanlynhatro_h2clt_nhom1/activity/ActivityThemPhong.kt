package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ListLoaiDichVuAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.util.UUID

class ActivityThemPhong : AppCompatActivity() {
    private lateinit var binding:ActivityThemPhongBinding
    private var maKhu=""
    private var soPhong:Int?=null
    private  var soTang=""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityThemPhongBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val phongDao=PhongDao(this)
        val loaiDichVuDao=LoaiDichVuPhongDao(this)
        val listLoaiDichVu= mutableListOf(
           LoaiDichVu(
               gia_dich_vu = 3000,
               ma_loai_dich_vu = UUID.randomUUID().toString(),
               ma_phong = "",
               ten_loai_dich_vu = "Tiền điện",
               trang_thai_loai_dich_vu = 0,
           ),LoaiDichVu(
                gia_dich_vu = 20000,
                ma_loai_dich_vu = UUID.randomUUID().toString(),
                ma_phong = "",
                ten_loai_dich_vu = "Tiền nước",
                trang_thai_loai_dich_vu = 0,
            ),
            LoaiDichVu(
                gia_dich_vu = 50000,
                ma_loai_dich_vu = UUID.randomUUID().toString(),
                ma_phong = "",
                ten_loai_dich_vu = "Tiền mạng",
                trang_thai_loai_dich_vu = 1,
            ),
            LoaiDichVu(
                gia_dich_vu = 200000,
                ma_loai_dich_vu = UUID.randomUUID().toString(),
                ma_phong = "",
                ten_loai_dich_vu = "Tiền điều hòa",
                trang_thai_loai_dich_vu = 2,
            )
        )
        val srf=getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        maKhu=srf.getString(MA_KHU_KEY, "")!!
//        binding.setOnCheckedChangeListener { compoundButton, b ->
//            if (b){
//                binding.themSoPhongParrent.visibility=View.VISIBLE
//            }
//            else{
//                binding.themSoPhongParrent.visibility=View.GONE
//                binding.edSoPhongTro.setText("")
//            }
//        }
        binding.switchTuDongTang.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                binding.soTangParrent.visibility = View.VISIBLE

            }
            else{
                binding.soTangParrent.visibility = View.GONE
                binding.edSoTang.setText("")
            }
        }
        binding.btnThemDichVu.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listLoaiDichVuAdapter=ListLoaiDichVuAdapter(listLoaiDichVu, this@ActivityThemPhong)
            dialog.listLoaiDichVu.adapter=listLoaiDichVuAdapter
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->

            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.dismiss()
            }
                build.setView(dialog.root)
                build.show()
        }
        binding.btnLuuThemPhong.setOnClickListener {
            soPhong=binding.edSoPhongTro.text.toString().toIntOrNull() ?: 1
            soTang=binding.edSoTang.text.toString()
            repeat(soPhong!!){
                val tenMacDinh=binding.edTenPhongTro.text.toString()
                val maDichVuPhong=UUID.randomUUID().toString()
                val phong=Phong(
                    ma_phong = UUID.randomUUID().toString(),
                    ten_phong = "$tenMacDinh$soTang${it+1}",
                    dien_tich = binding.edDienTichPhong.text.toString().toInt(),
                    gia_thue = binding.edGiaThue.text.toString().toLong(),
                    so_nguoi_o = 0,
                    trang_thai_phong = 0,
                    ma_khu = maKhu
                )
                phongDao.insertPhong(phong)

            }



        }

        binding.btnHuyThemPhong.setOnClickListener {
            this@ActivityThemPhong.finish()
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
    fun thongBaoLuu(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo ")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })

        bundle.show()
    }
    fun thongBaoThanhCong(loi: String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@ActivityThemPhong,ActivityDanhSachPhong::class.java)
            startActivity(intent)
            finish()
        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun validate():Int{
        var check = -1
        if(binding.edTenPhongTro.text.toString().isNotBlank()&&
                (binding.edSoPhongTro.text.toString().toIntOrNull()!=null)||
                binding.edDienTichPhong.text.toString().toIntOrNull()!=null&&
                binding.edGiaDien.text.toString().toIntOrNull()!=null&&
                binding.edGiaNuoc.text.toString().toIntOrNull()!=null||
                binding.edSoTang.text.toString().toIntOrNull()!=null
                ){
            check = 1
        }
        return check

}

}