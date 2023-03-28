package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogHoaDonChiTietBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*

class HoaDonViewHolder(
   var binding: LayoutItemHoaDonBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(hoaDon: HoaDon){

        if (hoaDon.trang_thai_hoa_don==0 ){
            binding.tvTrangThaiHoaDon.isChecked = false
            val phong = PhongDao(binding.root.context).getPhongById(hoaDon.ma_phong)
            binding.tvTrangThaiHoaDon.setOnClickListener {
                val hoaDon = HoaDon(
                    ma_phong = hoaDon.ma_phong,
                    ma_hoa_don = hoaDon.ma_hoa_don,
                    gia_thue = hoaDon.gia_thue,
                    mien_giam = hoaDon.mien_giam,
                    gia_dich_vu = hoaDon.gia_dich_vu,
                    trang_thai_hoa_don = 1,
                    ngay_tao_hoa_don = hoaDon.ngay_tao_hoa_don,
                    so_nuoc = hoaDon.so_nuoc,
                    so_dien = hoaDon.so_dien,
                    tong = hoaDon.tong
                )
                 val builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle("Thông báo thanh toán")
                builder.setMessage("Xác nhận thanh toán!")
            builder.setPositiveButton("Xác nhận", DialogInterface.OnClickListener { dialog, which ->
                    val dao = HoaDonDao(binding.root.context).update(hoaDon)
                    dialog.cancel()
            })
                 builder.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
                     dialog.dismiss()
                     binding.tvTrangThaiHoaDon.isChecked = false
                 })

            builder.show()
            }
            binding.tvTenPhong.text = phong?.ten_phong

            binding.tvTong.text = hoaDon.tong.toString()
            binding.tvConLai.text = hoaDon.tong.toString()
            binding.layoutChuyenChiTietHoaDon.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(binding.root.context)
                val dialog = DialogHoaDonChiTietBinding.inflate(LayoutInflater.from(binding.root.context))
                bottomSheetDialog.setContentView(dialog.root)
                val simpleDateFormat = SimpleDateFormat("MM-yyyy")
                val date: String = simpleDateFormat.format(Date())

                dialog.tvTenPhong.text = phong?.ten_phong
                dialog.tvNgay.text = hoaDon.ngay_tao_hoa_don
                dialog.tvTienPhong.text = hoaDon.gia_thue.toString() + " Vnd"
                dialog.tvGiaDichVu.text = hoaDon.gia_dich_vu.toString() +" Vnd"
                dialog.tvSoDien.text = hoaDon.so_dien.toString() + " Số"
                dialog.tvKhoiNuoc.text = hoaDon.so_nuoc.toString() + " Khối"
                dialog.tvTienMienGiam.text = hoaDon.mien_giam.toString() + " Vnd"
                dialog.tvNgayHoaDon.text = "Hóa đơn tháng "+date
                dialog.chkThanhToan.isChecked = false


                dialog.tvTongTien.text = hoaDon.tong.toString()
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
class HoaDonAdapter(val list: List<HoaDon>): RecyclerView.Adapter<HoaDonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoaDonViewHolder {
      val infa = LayoutInflater.from(parent.context)
        val binding = LayoutItemHoaDonBinding.inflate(infa,parent,false)
        return HoaDonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoaDonViewHolder, position: Int) {
       val hoaDon = list[position]
        holder.apply {
            bind(hoaDon)
        }
    }

    override fun getItemCount()= list.size

}