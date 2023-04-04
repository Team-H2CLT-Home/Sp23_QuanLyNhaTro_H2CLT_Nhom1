package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HoaDonAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongChuaDongTienBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityPhongSapHetHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*

class ActivityPhongChuaDongTien : AppCompatActivity() {
    private lateinit var binding: ActivityPhongChuaDongTienBinding
    private var maKhu = ""
    private var list = listOf<HoaDon>()
    private var date=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhongChuaDongTienBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachPhong
        setSupportActionBar(binding.tbDanhSachPhong)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "")!!
        val sdf = SimpleDateFormat("yyyy-MM")
        date=sdf.format(Date()).toString()
        onResume()
    }

    fun chuyenActivity(){
        val intent = Intent(this@ActivityPhongChuaDongTien, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
    override fun onResume() {
        super.onResume()

        val hoaDonDao = HoaDonDao(binding.root.context)
        list = hoaDonDao.getAllInHoaDonByMaKhu(maKhu).filter { it.trang_thai_hoa_don==0 && date in it.ngay_tao_hoa_don }
        val hoaDonAdapter = HoaDonAdapter(list)
        binding.rcyPhongChuaDongTien.adapter = hoaDonAdapter
        hoaDonAdapter.notifyDataSetChanged()
    }
}