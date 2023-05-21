package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityCaiDatThongTinBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinChuNhaBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin

class ActivityCaiDatThongTin : AppCompatActivity() {
    private lateinit var binding: ActivityCaiDatThongTinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val tenDao = AdminDao(this).getHoTenAdmin()
//        val stdDao = AdminDao(this).getSDTAdmin()
//        val stkDao = AdminDao(this).getSTKAdmin()
//        val ngaySinhDao = AdminDao(this).getNSAdmin()
        binding = ActivityCaiDatThongTinBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
        setContentView(binding.root)
////        val adminDao = AdminDao(this)
////        list = adminDao.getAllInAdmin()
//        binding.tvTenChuNha.text = tenDao
//        binding.tvSoDienThoai.text = stdDao
//        binding.btnCapNhatThongTin.setOnClickListener {
//            if(binding.edNgaySinh.text.toString().isNotBlank()&&binding.edSoTaiKhoan.text.toString().isNotBlank()){
//                val admin = Admin (
//                sdt = binding.tvSoDienThoai.text.toString(),
//                ten_dang_nhap = "",
//                ho_ten = binding.tvTenChuNha.text.toString(),
//                stk = binding.edSoTaiKhoan.text.toString(),
//                ngay_sinh = binding.edNgaySinh.text.toString(),
//                mat_khau = ""
//                )
//                val dao = AdminDao(this@ActivityCaiDatThongTin).updateAdmin(admin)
//                if (dao>0){
//                    thongBaoThanhCong("Bạn đã cập nhật thành công !!!")
////                    val intent = Intent(this@ActivityCaiDatThongTin,)
//
//                }else{
//                    thongBaoLoi("Cập nhật thông tin thất bại !!!")
//                }
//            }else{
//                thongBaoLoi("Nhập đủ thông tin")
//            }
//        }
//    }
//    fun thongBaoLoi(loi : String){
//        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
//        bundle.setTitle("Thông Báo Lỗi")
//        bundle.setMessage(loi)
//        bundle.setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, which ->
//            dialog.cancel()
//        })
//        bundle.show()
//    }
//    fun thongBaoThanhCong(loi : String){
//        val bundle = AlertDialog.Builder(this)
//        bundle.setTitle("Thông Báo")
//        bundle.setMessage(loi)
//        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
//            val intent = Intent(this@ActivityCaiDatThongTin,ActivityDangNhap::class.java)
//            startActivity(intent)
//            finish()
//        })
//        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
//            dialog.cancel()
//        })
//        bundle.show()
//    }
    }
}