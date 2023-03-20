package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.ViewpagerDanhSachHoaDonAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachHoaDonBinding


class ActivityDanhSachHoaDon : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachHoaDonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDanhSachHoaDon
        setSupportActionBar(binding.tbDanhSachHoaDon)
        val ab = getSupportActionBar()
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
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
}