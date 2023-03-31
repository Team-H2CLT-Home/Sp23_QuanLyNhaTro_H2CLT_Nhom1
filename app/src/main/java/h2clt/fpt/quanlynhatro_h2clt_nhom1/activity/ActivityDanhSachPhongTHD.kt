package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.DanhSachPhongDaOAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDanhSachTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung

const val Ma_PHONG_KEY = "Ma Phong"
class ActivityDanhSachPhongTHD : AppCompatActivity() {
    private lateinit var binding: ActivityDanhSachTaoHoaDonBinding
    var list = mutableListOf<NguoiDung>()
    var maKhu = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDanhSachTaoHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbTaoHoaDon)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)

        val phongDao= NguoiDungDao(binding.root.context)
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "").toString()
        list= phongDao.getAllInNguoiDungByMaKhu(maKhu) as MutableList<NguoiDung>

        val danhSachPhongDaOAdapter = DanhSachPhongDaOAdapter(list,this@ActivityDanhSachPhongTHD)
        binding.recActivityTaoHopDong1.adapter = danhSachPhongDaOAdapter
        danhSachPhongDaOAdapter.notifyDataSetChanged()


    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
}