package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.NguoiThueAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.AdminDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinChuNhaBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung

class ActivityThongTinChuNha : AppCompatActivity() {
    private var maKhu=""

    private lateinit var binding: ActivityThongTinChuNhaBinding
    var list = listOf<Admin>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinChuNhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//        maKhu = srf.getString(MA_KHU_KEY, "")!!

        setSupportActionBar(binding.tbThongTinChuNha)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)

        val i = intent
        val admin:Admin = i.getSerializableExtra("admin") as Admin
//        val tenDao = AdminDao(this).getHoTenAdmin()
//        val stdDao = AdminDao(this).getSDTAdmin()
//        val stkDao = AdminDao(this).getSTKAdmin()
//        val ngaySinhDao = AdminDao(this).getNSAdmin()

//        val adminDao = AdminDao(this)
//        list = adminDao.getAllInAdmin()
        binding.tvTenChuNha.text = admin.ho_ten
        binding.tvSoDienThoai.text = admin.sdt
        if (admin.ngan_hang.equals("") && admin.stk.equals("")){
            binding.linearSoTaiKhoan.isVisible = false
            binding.linearNganHang.isVisible = false
        }else{
            binding.linearSoTaiKhoan.isVisible = true
            binding.linearNganHang.isVisible = true
            binding.tvSoTaiKhoan.text = admin.stk
            binding.tvNganHang.text = admin.ngan_hang
        }

        binding.tvNgaySinh.text = admin.ngay_sinh
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId();
        if (id == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }

}