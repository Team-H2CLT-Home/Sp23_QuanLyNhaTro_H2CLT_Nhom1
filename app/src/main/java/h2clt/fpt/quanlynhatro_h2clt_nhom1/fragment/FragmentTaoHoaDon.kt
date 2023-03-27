package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityManHinhChinhChuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentTaoHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.text.SimpleDateFormat
import java.util.*


class FragmentTaoHoaDon : AppCompatActivity() {
    private lateinit var binding: FragmentTaoHoaDonBinding
    val list = mutableListOf<Phong>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTaoHoaDonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listViewTaoHoaDon.adapter

        binding.tbTaoHoaDon
        setSupportActionBar(binding.tbTaoHoaDon)
        val ab = supportActionBar
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)

//        binding.seachViewHoaDon.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            android.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                var list1 = PhongDao(application).getAllInPhong()
//                if (newText!=null){
//                    seachView(list1,newText)
//                }
//                return false
//            }
//
//        } )


        binding.btnLuuHoaDon.setOnClickListener {


            if (validate()<1){
               thongBaoLoi("Nhập đầy đủ thông tin")
            }else{
                val id= UUID.randomUUID().toString()

                val simpleDateFormat = SimpleDateFormat("yyyy-MM")
                val date : String = simpleDateFormat.format(Date())

                var check  : Int
                if (binding.chkDaThanhToan.isChecked==true){ check = 1 }
                else{ check = 0 }


                val hoaDon = HoaDon(
                    ma_hoa_don = id,
                    ma_phong = binding.edTenPhongTro.text.toString(),
                    gia_thue = binding.edGiaThue.text.toString().trim().toInt(),
                    so_dien = binding.edSoDie.text.toString().trim().toInt(),
                    so_nuoc = binding.edKhoiNuoc.text.toString().toInt(),
                    gia_dich_vu = binding.edGiaDichVu.text.toString().toInt(),
                    mien_giam = binding.edTienMienGiam.text.toString().toInt(),
                    trang_thai_hoa_don = check,
                    ngay_tao_hoa_don = date

                )
                val daoHoaDon = HoaDonDao(binding.root.context).insertHoaDon(hoaDon)
                if (daoHoaDon>0){
                    thongBaoLoi("Thêm Thành Công")
                }else{
                    thongBaoLoi("Thêm Không Thành Công")
                }
            }

        }
        binding.btnHuyHoaDon.setOnClickListener {
            binding.edTenPhongTro.setText("")
            binding.edGiaThue.setText("")
            binding.edSoDie.setText("")
            binding.edKhoiNuoc.setText("")
            binding.edGiaDichVu.setText("")
            binding.edTienMienGiam.setText("")
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
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(this)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }

    fun chuyenActivity(){
        val intent = Intent(this@FragmentTaoHoaDon, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.itemId;
        if (id==android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
    fun seachView(list : List<Phong>, s : String):List<Phong>{
        return list.filter { it.ten_phong.contains(s) }
    }
}