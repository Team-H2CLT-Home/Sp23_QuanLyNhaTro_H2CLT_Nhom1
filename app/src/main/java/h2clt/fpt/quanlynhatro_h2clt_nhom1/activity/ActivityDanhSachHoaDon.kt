package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_PHONG_HOA_DON_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ViewpagerDanhSachHoaDonAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachHoaDonBinding
import java.text.SimpleDateFormat
import java.util.*


class ActivityDanhSachHoaDon : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachHoaDon
        val maPhong=intent.getStringExtra(MA_PHONG_HOA_DON_KEY)
        val phong = maPhong?.let { PhongDao(this).getPhongById(it) }!!
        Toast.makeText(binding.root.context,""+phong.ten_phong,Toast.LENGTH_LONG).show()

//        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val newDate = dateFormat.parse(hoaDon.ngay_tao_hoa_don)
//        val calendar = Calendar.getInstance()
//        if (newDate != null) {
//            calendar.time = newDate
//        }
//        val month = calendar.get(Calendar.MONTH)+1
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//        val year = calendar.get(Calendar.YEAR)

        setSupportActionBar(binding.tbDanhSachHoaDon)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val adapter = ViewpagerDanhSachHoaDonAdapter(supportFragmentManager, lifecycle)
        binding.viewpagerHoaDon.adapter = adapter
        TabLayoutMediator(binding.tabDanhSachHoaDon, binding.viewpagerHoaDon) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = "Đã thanh toán"
                }
                1 -> {
                    tab.text = "Chưa thanh toán"
                }
                else -> tab.text = "Đã thanh toán"
            }
        }.attach()
    }
    fun chuyenActivity(){
        val intent = Intent(this@ActivityDanhSachHoaDon, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
}