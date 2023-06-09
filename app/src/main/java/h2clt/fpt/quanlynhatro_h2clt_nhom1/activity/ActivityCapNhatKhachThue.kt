package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityCapNhatKhachThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentPhongTrongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import java.util.*

class ActivityCapNhatKhachThue : AppCompatActivity() {
    private var maKhu = ""
    private var maPhong =""
    private var maPhongCu=""
    var listNguoiDung= listOf<NguoiDung>()
    private lateinit var binding: ActivityCapNhatKhachThueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapNhatKhachThueBinding.inflate(layoutInflater)

        setSupportActionBar(binding.tbCapNhatKhachThue)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
//        listNguoiDung=NguoiDungDao(requireActivity()).getAllInNguoiDungByMaKhu(maKhu)
        listNguoiDung=NguoiDungDao(this).getAllInNguoiDangOByMaKhu(maKhu)


//        val maPhong = intent.getStringExtra(MA_PHONG_NGUOI_DUNG_KEY)
        val i = intent
        val nguoiDung: NguoiDung = i.getSerializableExtra("khachThue") as NguoiDung
        maPhongCu=nguoiDung.ma_phong
        binding.edHoTenSuaNguoiDung.setText(nguoiDung.ho_ten_nguoi_dung)
        binding.edCCCDSuaNguoiDung.setText(nguoiDung.cccd)
        binding.edNgaySinhSuaNguoiDung.setText(nguoiDung.nam_sinh)
        binding.edQueQuanSuaNguoiDung.setText(nguoiDung.que_quan)
        binding.edSDTSuaNguoiDung.setText(nguoiDung.sdt_nguoi_dung)


        /////////
        val listPhong=PhongDao(this).getAllInPhongByMaKhu(maKhu)
        val spinner=MaPhongSpinner(this, listPhong)
        binding.spinnerSuaNguoiDung.adapter=spinner
        binding.spinnerSuaNguoiDung.onItemSelectedListener=object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                maPhong=listPhong[position].ma_phong
//                    Toast.makeText(activity, maPhong, Toast.LENGTH_SHORT).show()
                Toast.makeText(this@ActivityCapNhatKhachThue, "Tên phòng: "+listPhong[position].ten_phong, Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        var posND = 0
        for (i in listPhong.indices) {
            if (nguoiDung.ma_phong == listPhong[i].ma_phong) {
                posND = i
            }
        }
//
//        Log.d("aaa", NguoiDungDao(this).getMaNguoiDangOByMaPhong(nguoiDung.ma_phong))
//        Toast.makeText(binding.root.context, NguoiDungDao(this).getMaNguoiDangOByMaPhong(nguoiDung.ma_phong), Toast.LENGTH_SHORT).show()
        binding.spinnerSuaNguoiDung.setSelection(posND)
        Toast.makeText(this@ActivityCapNhatKhachThue,"Mã phòng:"+ (posND), Toast.LENGTH_SHORT).show()
//        Log.d("aaa","ma phong"+binding.spinnerSuaNguoiDung.setSelection(posND))
        if(nguoiDung.ma_nguoi_dung!=(NguoiDungDao(this).getMaNguoiDangOByMaPhong(nguoiDung.ma_phong)) ) {
//            var posND = 0
//            for (i in 0 until listPhong.size) {
//                if (nguoiDung.ma_phong == listPhong.get(i).ma_phong) {
//                    posND = i
//                }
//            }
//            binding.spinnerSuaNguoiDung.setSelection(posND)
            binding.spinnerSuaNguoiDung.visibility = View.VISIBLE
//            binding.lyTenPhong.visibility = View.GONE
        }else{
            binding.spinnerSuaNguoiDung.visibility = View.VISIBLE
            binding.spinnerSuaNguoiDung.isEnabled = false
            binding.chkTrangThaiKhachThue.isEnabled = false
//            binding.spinnerSuaNguoiDung
//            binding.lyTenPhong.visibility = View.VISIBLE
//            binding.edTenPhong.setText(PhongDao(this).getTenPhongById(nguoiDung.ma_phong))
        }


        binding.btnLuuSuaNguoiDung.setOnClickListener {
            if(binding.chkTrangThaiKhachThue.isChecked){
                NguoiDungDao(binding.root.context).updateTrangThaiNguoiDungThanhDaO(nguoiDung.ma_nguoi_dung)
                thongBaoXoa("Xác nhận xoá? ")
                if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhongCu).isEmpty()){
                    PhongDao(this@ActivityCapNhatKhachThue).updateTrangThaiPhongThanhDaO(maPhongCu)
                }
            }else{
                if(binding.edHoTenSuaNguoiDung.text.toString().isNotBlank()&&binding.edNgaySinhSuaNguoiDung.text.toString().isNotBlank()
                    &&binding.edSDTSuaNguoiDung.text.toString().isNotBlank()&&binding.edCCCDSuaNguoiDung.text.toString().isNotBlank()
                    &&binding.edQueQuanSuaNguoiDung.text.toString().isNotBlank()){
                    val listNguoiDungByMaPhong = NguoiDungDao(this).getListNguoiDungByMaPhong(maPhong)
                    val soNguoiO = NguoiDungDao(this).getSoNguoiOByMaPhong(maPhong)
//                    Toast.makeText(binding.root.context, soNguoiO, Toast.LENGTH_SHORT).show()

                    if(listNguoiDungByMaPhong.size<soNguoiO){
//                    val maNguoiDung = UUID.randomUUID().toString()
                        if(nguoiDung.ma_nguoi_dung!=(NguoiDungDao(this).getMaNguoiDangOByMaPhong(nguoiDung.ma_phong)) ){

                            val nguoiDungMoi = NguoiDung(
                                ma_nguoi_dung = nguoiDung.ma_nguoi_dung,
                                ho_ten_nguoi_dung = binding.edHoTenSuaNguoiDung.text.toString(),
                                nam_sinh = binding.edNgaySinhSuaNguoiDung.text.toString(),
                                sdt_nguoi_dung = binding.edSDTSuaNguoiDung.text.toString(),
                                que_quan = binding.edQueQuanSuaNguoiDung.text.toString(),
                                cccd = binding.edCCCDSuaNguoiDung.text.toString(),
                                trang_thai_chu_hop_dong = 0,
                                trang_thai_o = 1,
                                ma_phong = maPhong
                            )

                            val dao = NguoiDungDao(this).updateNguoiDung(nguoiDungMoi)
                            if (dao > 0) {
                                if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhongCu).isEmpty()){
                                    PhongDao(this@ActivityCapNhatKhachThue).updateTrangThaiPhongThanhDaO(maPhongCu)

                                }
                                if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhong)
                                        .isNotEmpty()
                                ){
                                    PhongDao(this@ActivityCapNhatKhachThue).updateTrangThaiPhongThanhDangO(maPhong)

                                }
//                        PhongDao(binding.root.context).updateTrangThaiPhongThanhDangO(maPhong)
                                thongBaoThanhCong("Sửa người dùng thành công")
//                            Snackbar.make(it, "Sửa khách thuê thành công", Toast.LENGTH_SHORT).show()
                            } else {
//                            Snackbar.make(it, "Sửa khách thuê thành công", Toast.LENGTH_SHORT).show()
                                thongBaoLoi("Sửa người dùng không thành công")
                            }
//                        if(NguoiDungDao(this).getListNguoiDungByMaPhong(maPhong).size ==0){
//                            PhongDao(binding.root.context).updateTrangThaiPhongThanhDaO(maPhong)
//                        }
                        }else{
//                        thongBaoLoi("Không được sửa thông tin chủ hợp đồng")
//                           if(nguoiDung.ma_phong==)
                            val nguoiDungMoi = NguoiDung(
                                ma_nguoi_dung = nguoiDung.ma_nguoi_dung,
                                ho_ten_nguoi_dung = binding.edHoTenSuaNguoiDung.text.toString(),
                                nam_sinh = binding.edNgaySinhSuaNguoiDung.text.toString(),
                                sdt_nguoi_dung = binding.edSDTSuaNguoiDung.text.toString(),
                                que_quan = binding.edQueQuanSuaNguoiDung.text.toString(),
                                cccd = binding.edCCCDSuaNguoiDung.text.toString(),
                                trang_thai_chu_hop_dong = 1,
                                trang_thai_o = 1,
                                ma_phong = maPhong
                            )
                            val dao = NguoiDungDao(this).updateNguoiDung(nguoiDungMoi)
                            if (dao > 0) {
                                if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhongCu).isEmpty()){
                                    PhongDao(this@ActivityCapNhatKhachThue).updateTrangThaiPhongThanhDaO(maPhongCu)
                                }
                                if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhong)
                                        .isNotEmpty()
                                ){
                                    PhongDao(this@ActivityCapNhatKhachThue).updateTrangThaiPhongThanhDangO(maPhong)

                                }
                                thongBaoThanhCong("Sửa người dùng thành công")
//                            Snackbar.make(it, "Sửa khách thuê thành công", Toast.LENGTH_SHORT).show()
                            } else {
//                            Snackbar.make(it, "Sửa khách thuê ko thành công", Toast.LENGTH_SHORT).show()
                                thongBaoLoi("Sửa người dùng không thành công")
                            }

//                        return@setOnClickListener
                        }
//                    onResume()
                    }else{
                        thongBaoLoi("Phòng đã đủ người")
                        return@setOnClickListener
                    }


                }else{
                    thongBaoLoi("Dữ liệu không được để trống!!!")
                }
            }



        }
        binding.btnHuySuaNguoiDung.setOnClickListener {
//            val intent = Intent(this@ActivityCapNhatKhachThue,ActivityDanhSachNguoiThue::class.java)
//            startActivity(intent)
            finish()
        }
//        binding.edHoTenSuaNguoiDung.text =

        setContentView(binding.root)

    }
    fun thongBaoXoa(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông báo xóa!!!")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Xác nhận xóa", DialogInterface.OnClickListener { dialog, which ->
//            val intent = Intent(this@ActivityCapNhatKhachThue,ActivityDanhSachNguoiThue::class.java)
//            startActivity(intent)
            finish()
            dialog.cancel()
//            onResume()

        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun thongBaoThanhCong(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
//            val intent = Intent(this@ActivityCapNhatKhachThue,ActivityDanhSachNguoiThue::class.java)
//            startActivity(intent)
            finish()
            dialog.cancel()
//            onResume()

        })
//        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
//            dialog.cancel()
//        })
        bundle.show()
    }
    fun thongBaoLoi(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    override fun onResume() {
        super.onResume()
        reload()
    }
    private fun reload(){
        val nguoiDungDao= this?.let { NguoiDungDao(it) }!!
//        listNguoiDung=nguoiDungDao.getAllInNguoiDungByMaKhu(maKhu)
        listNguoiDung=nguoiDungDao.getAllInNguoiDangOByMaKhu(maKhu)
        val nguoiThueAdapter=NguoiThueAdapter(listNguoiDung,object :KhachThueInterface{
            override fun OnClickKhachThue(pos: Int) {
                val nguoiDung = listNguoiDung.get(pos)
                val intent = Intent(this@ActivityCapNhatKhachThue, ActivityCapNhatKhachThue::class.java)
                intent.putExtra("khachThue",nguoiDung)
                startActivity(intent)
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId();
        if (id == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }

}


