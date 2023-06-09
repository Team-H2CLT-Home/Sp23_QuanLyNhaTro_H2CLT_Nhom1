package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.TablayoutQuanlyBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class FragmentQuanLy:Fragment() {
    private lateinit var binding:TablayoutQuanlyBinding
    var listHopDong= listOf<HopDong>()
    private var maKhu = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TablayoutQuanlyBinding.inflate(inflater,container,false)
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        binding.taoHopDong.setOnClickListener {
           val intent = Intent(context, ActivityTaoHopDong::class.java)
            startActivity(intent)
        }
        binding.TraPhong.setOnClickListener {
            updateHopDong()
            val intent = Intent(context, ActivityXuLyPhong::class.java)
            startActivity(intent)
        }
        binding.taoHoaDon.setOnClickListener {
            val intent = Intent(context, ActivityDanhSachPhongTHD::class.java)
            startActivity(intent)
        }
        binding.dsPhongThue.setOnClickListener {
            val intent = Intent(context, ActivityDanhSachPhong::class.java)
            startActivity(intent)
        }
        binding.dsKhachThue.setOnClickListener {
            val intent = Intent(context, ActivityDanhSachNguoiThue::class.java)
            startActivity(intent)
        }
        binding.dsHoaDon.setOnClickListener {
            val intent = Intent(context, ActivityDanhSachHoaDon::class.java)
            startActivity(intent)
        }
        binding.dsHopDong.setOnClickListener {
            updateHopDong()
            val intent = Intent(context, ActivityDanhSachHopDong::class.java)
            startActivity(intent)
        }
        binding.quanLyDichVu.setOnClickListener {
            val intent = Intent(context, ActivityQuanLyDichVu::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun updateHopDong() {
            listHopDong = HopDongDao(binding.root.context).getAllInHopDongByMaKhu(maKhu,1)
            for(i in 0 until  listHopDong.size){
                val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

                //============================
                val simpleDateFormatNow = SimpleDateFormat("yyyy-MM-dd")
                var mYearNow = 0
                var mMonthNow = 0
                var mDayNow = 0
                val c = Calendar.getInstance() as GregorianCalendar?
                mYearNow = (c as Calendar).get(Calendar.YEAR)
                mMonthNow = c!!.get(Calendar.MONTH)
                mDayNow = c!!.get(Calendar.DAY_OF_MONTH)
                val cNow = GregorianCalendar(mYearNow, mMonthNow, mDayNow)
                for (y in 0..7) {
                    if (y == 0) {
                        if (dateFormat.format(tinhNgaySapHetHanHopDong(listHopDong.get(i), y)!!.time) <= simpleDateFormatNow.format(cNow!!.time)) {
                            updateHDHetHan(listHopDong.get(i))
                            //reloadDanhSanhHD(binding)
                            //reloadDSHopDong()

                        }
                    } else {
                        if (dateFormat.format(tinhNgaySapHetHanHopDong(listHopDong.get(i), y)!!.time) == simpleDateFormatNow.format(cNow!!.time)) {
                            updateHDSapHetHan(listHopDong.get(i))
                            //reloadDSHopDong()

                        }
                    }
                }
            }
    }
    fun tinhNgaySapHetHanHopDong(hopDong: HopDong, a:Int): GregorianCalendar {
        val ngayHetHan = hopDong.ngay_hop_dong
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val newDate = dateFormat.parse(ngayHetHan)
        val calendar = Calendar.getInstance()
        if (newDate != null) {
            calendar.time = newDate
        }

        val monthNgaySapHetHan = calendar.get(Calendar.MONTH)
        val dayNgaySapHetHan = calendar.get(Calendar.DAY_OF_MONTH) - a
        val yearNgaySapHetHan = calendar.get(Calendar.YEAR)
        return GregorianCalendar(yearNgaySapHetHan, monthNgaySapHetHan, dayNgaySapHetHan)
    }

    fun updateHDHetHan(hopDong: HopDong){
        val hopDongNew = HopDong(
            ma_hop_dong = hopDong.ma_hop_dong,
            ma_phong = hopDong.ma_phong,
            ma_nguoi_dung = hopDong.ma_nguoi_dung,
            thoi_han = hopDong.thoi_han,
            ngay_o = hopDong.ngay_o,
            ngay_hop_dong = hopDong.ngay_hop_dong,
            tien_coc = hopDong.tien_coc,
            anh_hop_dong = hopDong.anh_hop_dong,
            trang_thai_hop_dong = 0,
            hieu_luc_hop_dong = hopDong.hieu_luc_hop_dong,
            ngay_lap_hop_dong = hopDong.ngay_lap_hop_dong
        )
        HopDongDao(binding.root.context).updateHopDong(hopDongNew)
    }
    fun updateHDSapHetHan(hopDong: HopDong){
        val hopDongNew = HopDong(
            ma_hop_dong = hopDong.ma_hop_dong,
            ma_phong = hopDong.ma_phong,
            ma_nguoi_dung = hopDong.ma_nguoi_dung,
            thoi_han = hopDong.thoi_han,
            ngay_o = hopDong.ngay_o,
            ngay_hop_dong = hopDong.ngay_hop_dong,
            tien_coc = hopDong.tien_coc,
            anh_hop_dong = hopDong.anh_hop_dong,
            trang_thai_hop_dong = 2,
            hieu_luc_hop_dong = hopDong.hieu_luc_hop_dong,
            ngay_lap_hop_dong = hopDong.ngay_lap_hop_dong
        )
        HopDongDao(binding.root.context).updateHopDong(hopDongNew)
    }

}