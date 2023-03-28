package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_PHONG_HOA_DON_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*
class FragmentTaoHoaDon : AppCompatActivity() {
    private lateinit var binding : FragmentTaoHoaDonBinding
//    private var maKhu = ""
//    private var list = listOf<HoaDon>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentTaoHoaDonBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val maPhong=intent.getStringExtra(MA_PHONG_HOA_DON_KEY)
        val phong = maPhong?.let { PhongDao(this).getPhongById(it) }!!
//        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//        maKhu= srf.getString(MA_KHU_KEY, "")!!
//        list = HoaDonDao(this).getAllInHoaDonByMaKhu(maKhu)
        binding.edTenPhongTro.setText(phong.ten_phong)
        binding.edGiaThue.setText("${phong.gia_thue} VND")
        binding.edGiaDichVu.setText(""+300)
        binding.btnLuuHoaDon.setOnClickListener {
            val spf = SimpleDateFormat("yyyy-MM-dd")
            val date = spf.format(Date())
            if (validate()<1){
                thongBaoLoi("Nhập đầy đủ thông tin")
            }else{
                val id= UUID.randomUUID().toString()
                var check  : Int
                if (binding.chkDaThanhToan.isChecked){ check = 1 }
                else{ check = 0 }
                val hoaDon = HoaDon(
                    ma_hoa_don = id,
                    ma_phong = maPhong,
                    gia_thue = phong.gia_thue.toInt(),
                    so_dien = binding.edSoDie.text.toString().toInt(),
                    so_nuoc = binding.edKhoiNuoc.text.toString().toInt(),
                    gia_dich_vu = 300 ,
                    mien_giam = binding.edTienMienGiam.text.toString().toInt(),
                    trang_thai_hoa_don = check,
                    ngay_tao_hoa_don = date,
                )
                val daoHoaDon: Long = HoaDonDao(binding.root.context).insertHoaDon(hoaDon)

                if (daoHoaDon>0){
                    thongBaoLoi("Thêm Thành Công")
                }else{
                    thongBaoLoi("Thêm Không Thành Công")
                }
            }

        }
        binding.btnHuyHoaDon.setOnClickListener {
            binding.edTenPhongTro.setText("")
            binding.edGiaThue.setText("")
            binding.edSoDie.setText("")
            binding.edKhoiNuoc.setText("")
            binding.edGiaDichVu.setText("")
            binding.edTienMienGiam.setText("")
        }
    }
    fun validate(): Int {
        var check = -1
        if (binding.edSoDie.text.toString().isNotBlank() &&
            binding.edKhoiNuoc.text.toString().isNotBlank() &&
            binding.edTienMienGiam.text.toString().isNotBlank()) {
            check = 1
        }
        return check
    }
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
}