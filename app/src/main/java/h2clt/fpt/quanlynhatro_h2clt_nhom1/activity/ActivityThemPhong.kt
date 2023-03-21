package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.DichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.DichVuPhong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.util.UUID

class ActivityThemPhong : AppCompatActivity() {
    private lateinit var binding:ActivityThemPhongBinding
    private var maKhu=""
    private var soPhong:Int?=null
    private  var soTang=""
    private var loaiDichVuThem=LoaiDichVu(ma_loai_dich_vu ="",
    ten_loai_dich_vu = "",
    gia_dich_vu = 0,
    trang_thai_loai_dich_vu = 0,
    ma_dich_vu = "")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityThemPhongBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val phongDao=PhongDao(this)
        val loaiDichVuDao=LoaiDichVuPhongDao(this)
        val dichVuPhongDao=DichVuPhongDao(this)
        val srf=getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        maKhu=srf.getString(MA_KHU_KEY, "")!!
        binding.cbTaoTuDong.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                binding.themSoPhongParrent.visibility=View.VISIBLE
            }
            else{
                binding.themSoPhongParrent.visibility=View.GONE
                binding.edSoPhongTro.setText("")
            }
        }
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
            build.setView(dialog.root)
            dialog.btnLuuLoaiDV.setOnClickListener {
                if (dialog.edTenLoaiDV.text.toString()
                        .isNotBlank() &&( dialog.edGiaTienLoaiDV.text.toString().toIntOrNull()!=null)
                ) {
                   loaiDichVuThem=loaiDichVuThem.copy(ten_loai_dich_vu = dialog.edTenLoaiDV.text.toString())
                    loaiDichVuThem=loaiDichVuThem.copy(gia_dich_vu = dialog.edGiaTienLoaiDV.text.toString().toInt())
                    thongBaoLuu("Bạn đã lưu thành công!!!")
                    dialog.edTenLoaiDV.setText("")
                    dialog.edGiaTienLoaiDV.setText("")
                }else{
                    thongBaoLoi("Bạn đã nhập sai dữ liệu mời nhập lại !!!")
                }
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
                build.show()
        }
        binding.btnLuuThemPhong.setOnClickListener {
            soPhong=binding.edSoPhongTro.text.toString().toIntOrNull() ?: 1
            soTang=binding.edSoTang.text.toString()

            if(validate()<1){
                thongBaoLoi("bạn không được để trống !!!")
            }else{
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
                        ma_khu = maKhu,
                        ma_dich_vu = maDichVuPhong
                    )
                    val dichVuPhong=DichVuPhong(
                        ma_dich_vu = maDichVuPhong,
                        ten_dich_vu = "$tenMacDinh$soTang${it+1}"
                    )
                    val giaDien=LoaiDichVu(
                        ma_dich_vu = maDichVuPhong,
                        ma_loai_dich_vu = UUID.randomUUID().toString(),
                        gia_dich_vu = binding.edGiaDien.text.toString().toInt(),
                        trang_thai_loai_dich_vu = 0,
                        ten_loai_dich_vu = "Giá điện"
                    )
                    val giaNuoc=LoaiDichVu(
                        ma_dich_vu = maDichVuPhong,
                        ma_loai_dich_vu = UUID.randomUUID().toString(),
                        gia_dich_vu = binding.edGiaNuoc.text.toString().toInt(),
                        trang_thai_loai_dich_vu = 0,
                        ten_loai_dich_vu = "Giá nươc"
                    )
                    val rep : Long =  phongDao.insertPhong(phong)
                    val rep1: Long = dichVuPhongDao.insertDichVuPhong(dichVuPhong)
                    val rep2:Long = loaiDichVuDao.insertLoaiDichVuPhong(giaDien)
                    val rep3:Long = loaiDichVuDao.insertLoaiDichVuPhong(giaNuoc)
                    if(rep>0 && rep1>0 && rep2>0 && rep3>0){
                        thongBaoThanhCong("Bạn đã lưu thành công!!")
                    }else{
                        thongBaoLoi("Bạn lưu không thành công !!!")
                    }

                }
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