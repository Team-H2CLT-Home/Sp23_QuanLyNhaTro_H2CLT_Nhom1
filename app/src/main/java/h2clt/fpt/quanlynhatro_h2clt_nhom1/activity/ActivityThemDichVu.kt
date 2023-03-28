package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import java.util.UUID

private var maKhu=""
private val listLoaiThanhToan= listOf(
    Pair("Miễn phí/Không sử dung",0), Pair("Tính theo đầu người",2), Pair("Tính theo phòng",3),
    Pair("Tính theo đồng hồ", 1, )
)
private var trangThaiLoaiDichVu=1
private var listDichVu= mutableListOf<LoaiDichVu?>()
var loaiDichVu:LoaiDichVu?=null
class ActivityThemDichVu : AppCompatActivity() {
    private lateinit var binding:ActivityThemDichVuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityThemDichVuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val srf = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        setSupportActionBar(binding.tbQuanLyDichVu)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val loaiDichVuPhongDao=LoaiDichVuPhongDao(this)

        binding.btnThemLoaiDV.setOnClickListener{
            val tenDichVu=binding.edTenDichVu.text.toString()
            val giaDichVu=binding.edGiaDichVu.text.toString().toInt()
                 loaiDichVu = LoaiDichVu(
                    ma_loai_dich_vu = UUID.randomUUID().toString(),
                    ten_loai_dich_vu = tenDichVu,
                    gia_dich_vu = giaDichVu,
                    trang_thai_loai_dich_vu = trangThaiLoaiDichVu,
                    ma_khu_tro = maKhu,
                    ma_phong = ""
                )
                val kq = loaiDichVuPhongDao.insertLoaiDichVuPhong(loaiDichVu!!)
                if (kq > 0) {
                    Snackbar.make(it, "Thêm dịch vụ thành công", Snackbar.LENGTH_SHORT).show()
                    listDichVu+=loaiDichVu
                }
        }
        binding.lnLoaiThanhToan.setOnClickListener {
            val build = AlertDialog.Builder(this).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(this))
            val listText= listLoaiThanhToan.map { it.first }
            val arrayAdapter= ArrayAdapter(this, R.layout.layout_item_chon_dich_vu, listText)
            dialog.listLoaiDichVu.adapter=arrayAdapter
            dialog.tvDichVuPhong.text="Loại hình thanh toán"
            dialog.listLoaiDichVu.setOnItemClickListener { adapterView, view, i, l ->
                binding.tvTenLoaiThanhToan.text= listLoaiThanhToan[i].first
                trangThaiLoaiDichVu= listLoaiThanhToan[i].second
                build.dismiss()
            }
            dialog.btnHuyLoaiDV.setOnClickListener {
                build.cancel()
            }
            build.setView(dialog.root)
            build.show()
        }
        binding.btnHuyLoaiDV.setOnClickListener{
            finish()
        }
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}