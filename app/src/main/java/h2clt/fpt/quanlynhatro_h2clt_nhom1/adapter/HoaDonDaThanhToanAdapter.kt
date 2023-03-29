package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.graphics.Color
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogHoaDonChiTietBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*

class HoaDonDaThanhToanViewHolder(
   var binding: LayoutItemHoaDonBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(hoaDon: HoaDon){
        var sum = 0
        var tongItem = 0
        ///
        if (hoaDon.trang_thai_hoa_don==1 ){
            // tach ngày trong hoa don

            val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val newDate = dateFormat.parse(hoaDon.ngay_tao_hoa_don)
            val calendar = Calendar.getInstance()
            if (newDate != null) {
                calendar.time = newDate
            }
            val month = calendar.get(Calendar.MONTH)+1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val year = calendar.get(Calendar.YEAR)
            //Toast.makeText(binding.root.context,month,Toast.LENGTH_LONG).show()


            binding.tvTrangThaiHoaDon.isChecked = true
            binding.tvTrangThaiHoaDon.isClickable = false
            val phong = PhongDao(binding.root.context).getPhongById(hoaDon.ma_phong)
            binding.tvTenPhong.text = phong?.ten_phong
            binding.tvThang.text = "T"+month.toString()
            binding.tvNam.text = year.toString()
            // tính tổng

            binding.tvTong.text = hoaDon.tong.toString()
            binding.tvTong.setTextColor(Color.argb(200,0,200,0))
            binding.tvDaThu.text =  hoaDon.tong.toString()
            binding.tvDaThu.setTextColor(Color.argb(200,0,200,0))
            binding.layoutChuyenChiTietHoaDon.setOnClickListener {
          //      tachThangNam(hoaDon.ngay_tao_hoa_don,binding.root.context)
                val bottomSheetDialog = BottomSheetDialog(binding.root.context)
                val dialog = DialogHoaDonChiTietBinding.inflate(LayoutInflater.from(binding.root.context))
                bottomSheetDialog.setContentView(dialog.root)

                dialog.tvTenPhong.text = phong?.ten_phong
                dialog.tvNgay.text = chuyenNgay1(hoaDon.ngay_tao_hoa_don)
                dialog.tvTienPhong.text = hoaDon.gia_thue.toString() + " Vnd"
                dialog.tvGiaDichVu.text = hoaDon.gia_dich_vu.toString() +" Vnd"
                dialog.tvSoDien.text = hoaDon.so_dien.toString() + " Số"
                dialog.tvKhoiNuoc.text = hoaDon.so_nuoc.toString() + " Khối"
                dialog.tvTienMienGiam.text = hoaDon.mien_giam.toString() + " Vnd"
                dialog.tvNgayHoaDon.text = "Hóa đơn tháng "+ chuyenNgay1(hoaDon.ngay_tao_hoa_don)
                dialog.chkThanhToan.isChecked = true





                dialog.tvTongTien.text = hoaDon.tong.toString()
                dialog.tvTongTien.setTextColor(Color.argb(200,0,200,0))
                dialog.btnDong.setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.show()
            }
        }
        else{
            binding.linerLayoutItemHD.isVisible  = false
        }
    }
}
fun chuyenNgay1(ngay : String ):String{
    val sdfNgay = SimpleDateFormat("yyyy-MM-dd")
    val objDate = sdfNgay.parse(ngay)
    val ngay =  DateFormat.format("MM-yyyy",objDate) as String
    return ngay
}
//fun tachThangNam(ngay : String,context: Context):String{
//  //  val stringOldDate = binding.edNgayBatDauO.text.toString()
//    //val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
//    val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
//    val newDate = dateFormat.parse(ngay)
//    val calendar = Calendar.getInstance()
//    if (newDate != null) {
//        calendar.time = newDate
//    }
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//    val year = calendar.get(Calendar.YEAR)
//    val c1 = GregorianCalendar(year, month, day)
//
//   // binding.edNgayHetHan.setText(simpleDateFormat.format(c1!!.time))
//    return ngay
//}
class HoaDonDaThanhToanAdapter(val list: List<HoaDon>): RecyclerView.Adapter<HoaDonDaThanhToanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoaDonDaThanhToanViewHolder {
      val infa = LayoutInflater.from(parent.context)
        val binding = LayoutItemHoaDonBinding.inflate(infa,parent,false)
        return HoaDonDaThanhToanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoaDonDaThanhToanViewHolder, position: Int) {
       val hoaDon = list[position]
        holder.apply {
            bind(hoaDon)
        }
    }

    override fun getItemCount()= list.size

}