package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.QuanLyDichVuAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityQuanLyDichVuBinding


private var maKhu=""
class ActivityQuanLyDichVu : AppCompatActivity() {
    private val listLoaiThanhToan= listOf(
        Pair("Miễn phí/Không sử dung",0), Pair("Tính theo đầu người",2), Pair("Tính theo phòng",3),
        Pair("Tính theo đồng hồ(Phổ biến)", 1, )
    )
    private lateinit var binding:ActivityQuanLyDichVuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuanLyDichVuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbQuanLyDichVu)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val srf = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        val loaiDichVuPhongDao = LoaiDichVuPhongDao(this)
        val listDichVu = loaiDichVuPhongDao.getAllInLoaiDichVuByKhuTro(maKhu)
        val adapter = QuanLyDichVuAdapter(listDichVu, this@ActivityQuanLyDichVu)
        binding.rcvListDichVu.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@ActivityQuanLyDichVu)
        }
        binding.imgThemDichVu.setOnClickListener {
            val intent=Intent(this@ActivityQuanLyDichVu, ActivityThemDichVu::class.java)
            startActivity(intent)

        }
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: called ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: called ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart: call ")
    }
}