package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.NguoiThueAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ThongTinChuNhaAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinChuNhaBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung

class ActivityThongTinChuNha : AppCompatActivity() {
    private var maKhu=""

    private lateinit var binding: ActivityThongTinChuNhaBinding
    var list = listOf<Admin>()
    val admin = Admin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//        maKhu = srf.getString(MA_KHU_KEY, "")!!
        val tenDao = AdminDao(this).getHoTenAdmin()
        val stdDao = AdminDao(this).getSDTAdmin()
        val stkDao = AdminDao(this).getSTKAdmin()
        val ngaySinhDao = AdminDao(this).getNSAdmin()
        binding = ActivityThongTinChuNhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val adminDao = AdminDao(this)
//        list = adminDao.getAllInAdmin()
        binding.tvTenChuNha.text = tenDao
        binding.tvSoDienThoai.text = stdDao
        binding.tvSoTaiKhoan.text = stkDao
        binding.tvNgaySinh.text = ngaySinhDao
        if(binding.tvSoTaiKhoan.text.equals("")){
            binding.linearSoTaiKhoan.setBackgroundColor(Color.parseColor("#EBEBEB"))
            binding.tvSTK.text = ""
        }
        if(binding.tvNgaySinh.text.equals("")){
            binding.linearNgaySinh.setBackgroundColor(Color.parseColor("#EBEBEB"))
            binding.tvNS.text = ""
        }
//        val adapter = ThongTinChuNhaAdapter(list)
//        binding.rcyThongTinChuNha.adapter = adapter


//        list = AdminDao(binding.root.context).getAllInAdmin() as MutableList<Admin>
//        list.filter { it.ho_ten==binding.tvTenChuNha.text }
    }

}