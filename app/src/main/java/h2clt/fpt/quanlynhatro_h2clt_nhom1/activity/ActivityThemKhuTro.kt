package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import java.util.UUID

const val SO_LUONG_PHONG_KEY="So_luong_phong"
const val MA_KHU_TU_TAO_KHU="ma khu khi tao khu"

class ActivityThemKhuTro : AppCompatActivity() {
    private lateinit var binding: ActivityThemKhuTroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemKhuTroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbThemKhuTro
        setSupportActionBar(binding.tbThemKhuTro )
        val ab = supportActionBar
        if (ab != null){
            ab.setHomeAsUpIndicator(R.drawable.black_left)
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        val srf=getSharedPreferences(THONG_TIN_DANG_NHAP, MODE_PRIVATE)
        val admin=srf.getString(USERNAME_KEY, "")!!
        binding.cbTaoTuDong.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                binding.SoPhongParent.visibility = View.VISIBLE
                binding.tvTuDongTaoPhong.visibility = View.VISIBLE
            }
            else{
                binding.tvTuDongTaoPhong.visibility=View.GONE
                binding.SoPhongParent.visibility=View.GONE
                binding.edSoPhong.setText("")
            }
        }
        binding.btnTiepTuc.setOnClickListener {
            if(!validate()){
                thongBaoLoi("Vui lòng nhập đủ thông tin!!!")
            }else{
                val id=UUID.randomUUID().toString()
                val khuTro=KhuTro(
                    ma_khu_tro = id,
                    ten_khu_tro = binding.edTenKhuTro.text.toString(),
                    so_luong_phong = binding.edSoPhong.text.toString().toInt(),
                    dia_chi = binding.edDiaChi.text.toString(),
                    ten_dang_nhap = admin
                )
                val dao=KhuTroDao(this@ActivityThemKhuTro).insertKhuTro(khuTro)
                if(dao>0){
                    val intent=Intent(this@ActivityThemKhuTro, ActivityTaoPhongKhiThemKhu::class.java)
                    intent.putExtra(SO_LUONG_PHONG_KEY, binding.edSoPhong.text.toString().toInt())
                    intent.putExtra(MA_KHU_TU_TAO_KHU, id)
                    startActivity(intent)
                    finishAffinity()
                }else{
                    thongBaoLoi("lưu thất bại")
                }
            }
        }

    }

    fun validate():Boolean{
        return (binding.edTenKhuTro.text.toString().isNotBlank()&&
                (binding.edSoPhong.text.toString().toIntOrNull()!=null)&&
                binding.edDiaChi.text.toString().isNotBlank())

    }

    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun thongBaoThanhCong(loi: String, id: String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@ActivityThemKhuTro,ActivityManHinhChinhChuTro::class.java)
            intent.putExtra(MA_KHU_KEY, id)
            startActivity(intent)
            finish()
        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }

    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}