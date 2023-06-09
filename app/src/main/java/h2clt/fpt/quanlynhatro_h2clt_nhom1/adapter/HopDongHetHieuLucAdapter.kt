package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityDanhSachHopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogChiTietHopDong2Binding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogChiTietHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemDsHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment.FragmentQuanLy
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.SimpleDateFormat
import java.util.*


class HopDongHetHieuLucViewHolder(
    val binding: LayoutItemDsHopDongBinding

): RecyclerView.ViewHolder(binding.root){


    fun bind(hopDong: HopDong){


        binding.tvDanhSachHopDongTenPhong.text=HopDongDao(binding.root.context).getTenPhongByIDHopDong(hopDong.ma_hop_dong)
        binding.tvDanhSachHopDongTenThanhVien.text= "Họ và tên: "+ HopDongDao(binding.root.context).getTenNguoiDungByIDHopDong(hopDong.ma_hop_dong)
        if (hopDong.trang_thai_hop_dong == 0){
            binding.tvDanhSachHopDongTrangThai.text = "Tình trạng hợp đồng: Hết hợp đồng"
            binding.tvDanhSachHopDongTrangThai.setTextColor(Color.RED)
        }else if (hopDong.trang_thai_hop_dong == 1){
            binding.tvDanhSachHopDongTrangThai.text = "Tình trạng hợp đồng: Còn hợp đồng"
            binding.tvDanhSachHopDongTrangThai.setTextColor(Color.BLACK)
        }else{
            binding.tvDanhSachHopDongTrangThai.text = "Tình trạng hợp đồng: Sắp hết hạn"
            binding.tvDanhSachHopDongTrangThai.setTextColor(Color.BLUE)
        }
        binding.tvDanhSachHopDongNgayO.text = chuyenDinhDangNgay(hopDong.ngay_o)
        binding.tvDanhSachHopDongNgayKetThuc.text = chuyenDinhDangNgay(hopDong.ngay_hop_dong)
        binding.tvDanhSachHopDongNgayLap.text = chuyenDinhDangNgay(hopDong.ngay_lap_hop_dong)
        binding.tvDanhSachHopDongTrangThai.setOnClickListener {
            val ngayHetHan = hopDong.ngay_hop_dong
            val dateFormat: java.text.DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val newDate = dateFormat.parse(ngayHetHan)
            val calendar = Calendar.getInstance()
            if (newDate != null) {
                calendar.time = newDate
            }
            val monthNgayHetHan = calendar.get(Calendar.MONTH)
            val dayNgayHetHan = calendar.get(Calendar.DAY_OF_MONTH) - 7
            val yearNgayHetHan = calendar.get(Calendar.YEAR)
            val cNgayHetHanHopDong = GregorianCalendar(yearNgayHetHan, monthNgayHetHan, dayNgayHetHan)
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
            Log.d("TAG", "updateHopDong: "+ (dateFormat.format(cNgayHetHanHopDong!!.time)))

        }

        binding.tvChiTietDanhSachHD.setOnClickListener {
              val build = BottomSheetDialog(binding.root.context)
              val dialog = DialogChiTietHopDong2Binding.inflate(LayoutInflater.from(binding.root.context))
            build.setContentView(dialog.root)
            dialog.tvTenPhong.setText(""+ HopDongDao(binding.root.context).getTenPhongByIDHopDong(hopDong.ma_hop_dong))
            dialog.tvTenChuHopDong.setText(""+ HopDongDao(binding.root.context).getTenNguoiDungByIDHopDong(hopDong.ma_hop_dong))
            dialog.tvThoiHan.setText(""+ hopDong.thoi_han + " tháng")
            dialog.tvNgayO.setText(""+ chuyenDinhDangNgay(hopDong.ngay_o))
            dialog.tvNgayKetThuc.setText(""+ chuyenDinhDangNgay(hopDong.ngay_hop_dong))
            dialog.tvThongTin.setText("Thông tin chi tiết về hợp đồng của phòng "+HopDongDao(binding.root.context).getTenPhongByIDHopDong(hopDong.ma_hop_dong))
            dialog.tvNgay.setText(""+ chuyenDinhDangNgay(hopDong.ngay_lap_hop_dong))
            dialog.tvTienCoc.setText(""+ hopDong.tien_coc)
            if (hopDong.trang_thai_hop_dong==1){
                dialog.chkThoiHan.setText("Còn hợp đồng")
                dialog.chkThoiHan.isChecked = true
            }else if(hopDong.trang_thai_hop_dong==0){
                dialog.chkThoiHan.setText("Hết hợp đồng")
                dialog.chkThoiHan.isChecked = false
            }else{
                dialog.chkThoiHan.setText("Sắp hết hợp đồng")
                dialog.chkThoiHan.isChecked = true
            }
            if (hopDong.hieu_luc_hop_dong==1){
                dialog.chkHieuLuc.setText("Còn hiệu lực")
                dialog.chkHieuLuc.isChecked = true
            }else{
                dialog.chkHieuLuc.setText("Hết hiệu lực")
                dialog.chkHieuLuc.isChecked = false
            }
            Toast.makeText(dialog.root.context,""+hopDong.trang_thai_hop_dong,Toast.LENGTH_SHORT).show()

            dialog.btnDong.setOnClickListener {

                build.dismiss()
            }
            build.show()
        }

    }


    private fun chuyenDinhDangNgay(ngay: String):String {
        val sdfNgay = SimpleDateFormat("yyyy-MM-dd")
        val dateFormatNgayO = DateFormat()
        val objDateNgayO = sdfNgay.parse(ngay)
        val ngay = DateFormat.format("dd/MM/yyyy", objDateNgayO) as String
        return ngay
    }
}
class HopDongHetHieuLucAdapter (val listHopDong: List<HopDong>): RecyclerView.Adapter<HopDongHetHieuLucViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HopDongHetHieuLucViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutItemDsHopDongBinding.inflate(inflater,parent,false)

        return  HopDongHetHieuLucViewHolder(binding)
    }
    override fun getItemCount()=listHopDong.size
    override fun onBindViewHolder(holder: HopDongHetHieuLucViewHolder, position: Int) {
        val hopDong = listHopDong[position]

        holder.apply {

            bind(hopDong)
            //updateHopDong(hopDong)



            //fragment.updateDSHopDong(hopDong)



        }

//        holder.binding.tvDanhSachHopDongTrangThai.setOnClickListener {
//            activity.updateHopDong(hopDong)
//        }
    }
}
