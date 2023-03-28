package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityTaoHoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_PHONG_HOA_DON_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import java.text.SimpleDateFormat
import java.util.*

class FragmentTaoHoaDon : AppCompatActivity() {
    private lateinit var binding: FragmentTaoHoaDonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentTaoHoaDonBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.tbTaoHoaDon1)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)

        val maPhong=intent.getStringExtra(MA_PHONG_HOA_DON_KEY)
        val phong = maPhong?.let { PhongDao(this).getPhongById(it) }!!
//        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//        maKhu= srf.getString(MA_KHU_KEY, "")!!
//        list = HoaDonDao(this).getAllInHoaDonByMaKhu(maKhu)
        val listND = NguoiDungDao(binding.root.context).getNguoiDungByMaPhong(maPhong)
        val soNguoiO = listND.filter { it.trang_thai_o == 1 }.size
        val listDV = LoaiDichVuPhongDao(binding.root.context).getAllInLoaiDichVuByPhong(maPhong)
        val giaDien = listDV.find { it.ten_loai_dich_vu=="Tiền điện" && it.trang_thai_loai_dich_vu==1 }?.gia_dich_vu
        val giaNuoc= listDV.find { it.ten_loai_dich_vu=="Tiền nước" && it.trang_thai_loai_dich_vu==1 }?.gia_dich_vu
        val dichVuTheoDauNguoi=listDV.filter { it.trang_thai_loai_dich_vu==2 }.sumOf { it.gia_dich_vu *soNguoiO }
        val dichVuTheoPhong=listDV.filter { it.trang_thai_loai_dich_vu==3 }.sumOf { it.gia_dich_vu }
        binding.edGiaDichVu.setText((dichVuTheoDauNguoi+dichVuTheoPhong).toString())

        binding.edTenPhongTro.setText(phong.ten_phong)
        binding.edGiaThue.setText("${phong.gia_thue} VND")
        binding.btnLuuHoaDon.setOnClickListener {
            val soDien=binding.edSoDie.text.toString().toInt()
            val soNuoc=binding.edKhoiNuoc.text.toString().toInt()
            val mienGiam=binding.edTienMienGiam.text.toString().toInt()
            val tong= (giaDien?.times(soDien) ?: 0) + (giaNuoc?.times(soNuoc) ?: 0)+dichVuTheoDauNguoi+dichVuTheoPhong-mienGiam+phong.gia_thue
            Toast.makeText(binding.root.context, tong.toString(),Toast.LENGTH_SHORT).show()
            val spf = SimpleDateFormat("yyyy-MM-dd")
            val date = spf.format(Date())
            if (validate()<1){
                thongBaoLoi("Nhập đầy đủ thông tin")
            }else{
                val id= UUID.randomUUID().toString()
                var check  : Int
                if (binding.chkDaThanhToan.isChecked){ check = 1 }
                else{ check = 0 }
                val hoaDon = HoaDon(
                    ma_hoa_don = id,
                    ma_phong = maPhong,
                    gia_thue = phong.gia_thue.toInt(),
                    so_dien = binding.edSoDie.text.toString().toInt(),
                    so_nuoc = binding.edKhoiNuoc.text.toString().toInt(),
                    gia_dich_vu = (dichVuTheoDauNguoi+dichVuTheoPhong).toInt() ,
                    mien_giam = binding.edTienMienGiam.text.toString().toInt(),
                    trang_thai_hoa_don = check,
                    ngay_tao_hoa_don = date,
                    tong = tong.toInt()
                )
                val daoHoaDon: Long = HoaDonDao(binding.root.context).insertHoaDon(hoaDon)

                if (daoHoaDon>0){
                    thongBaoXacNhan("Thêm Thành Công")
                }else{
                    thongBaoLoi("Thêm Không Thành Công")
                }
            }

        }
        binding.btnHuyHoaDon.setOnClickListener {
            binding.edSoDie.setText("")
            binding.edKhoiNuoc.setText("")
            binding.edTienMienGiam.setText("")
            binding.chkDaThanhToan.isChecked  = false
        }
    }
    fun validate(): Int {
        var check = -1
        if (binding.edSoDie.text.toString().isNotBlank() &&
            binding.edKhoiNuoc.text.toString().isNotBlank() &&
            binding.edTienMienGiam.text.toString().isNotBlank()) {
            check = 1
        }
        return check
    }
    fun thongBaoXacNhan(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@FragmentTaoHoaDon,ActivityTaoHoaDon::class.java)
            startActivity(intent)
            dialog.dismiss()
        })
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()

        })
        bundle.show()
    }
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()

        })
        bundle.show()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
    }