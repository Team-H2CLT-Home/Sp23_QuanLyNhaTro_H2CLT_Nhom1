package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThemKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro
import java.util.UUID


class ActivityThemKhuTro : AppCompatActivity() {
    private lateinit var binding: ActivityThemKhuTroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemKhuTroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTiepTuc.setOnClickListener {
            if(validate() < 1){
                thongBaoLoi("dữ liệu chưa chính xác !!!")
                return@setOnClickListener
            }else{
                val id=UUID.randomUUID().toString()
                val khuTro=KhuTro(
                    ma_khu_tro = id,
                    ten_khu_tro = binding.edTenKhuTro.text.toString(),
                    so_luong_phong = binding.edSoPhong.text.toString().toInt(),
                    dia_chi = binding.edDiaChi.text.toString(),
                    ten_dang_nhap = "admin"
                )
                val dao=KhuTroDao(this@ActivityThemKhuTro).insertKhuTro(khuTro)
                if(dao>0){
                    thongBaoThanhCong("lưu thành công")
                }else{
                    thongBaoLoi("lưu thất bại")
                }
            }
            Snackbar.make(it,"Thêm thành công",Snackbar.LENGTH_SHORT).show()
        }
    }

    fun validate():Int{
        var check=-1
        if(binding.edTenKhuTro.text.toString().isNotBlank()&&
                binding.edSoPhong.text.toString().isNotBlank()&&
                binding.edDiaChi.text.toString().isNotBlank()){
            check=1
        }
        return check
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
    fun thongBaoThanhCong(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@ActivityThemKhuTro,ActivityManHinhChinhChuTro::class.java)
            startActivity(intent)
        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
}