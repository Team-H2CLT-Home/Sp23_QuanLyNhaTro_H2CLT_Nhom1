package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogHoaDonChiTietBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*

class HoaDonViewHolder(
   var binding: LayoutItemHoaDonBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(hoaDon: HoaDon){
        var sum = 0
        var tongItem = 0
        if (hoaDon.trang_thai_hoa_don==0 ){
            binding.tvTrangThaiHoaDon.isChecked = false
            binding.tvTenPhong.text = hoaDon.ma_phong

            // tính tổng
            tongItem = (hoaDon.gia_thue + hoaDon.gia_dich_vu +
                    (hoaDon.so_dien*3500) + (hoaDon.so_nuoc*25000) - hoaDon.mien_giam)

            binding.tvTong.text = tongItem.toString()
            binding.layoutChuyenChiTietHoaDon.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(binding.root.context)
                val dialog = DialogHoaDonChiTietBinding.inflate(LayoutInflater.from(binding.root.context))
                bottomSheetDialog.setContentView(dialog.root)
                val simpleDateFormat = SimpleDateFormat("MM-yyyy")
                val date: String = simpleDateFormat.format(Date())

                dialog.tvTenPhong.text = hoaDon.ma_phong
                dialog.tvNgay.text = hoaDon.ngay_tao_hoa_don
                dialog.tvTienPhong.text = hoaDon.gia_thue.toString() + " Vnd"
                dialog.tvGiaDichVu.text = hoaDon.gia_dich_vu.toString() +" Vnd"
                dialog.tvSoDien.text = hoaDon.so_dien.toString() + " Số"
                dialog.tvKhoiNuoc.text = hoaDon.so_nuoc.toString() + " Khối"
                dialog.tvTienMienGiam.text = hoaDon.mien_giam.toString() + " Vnd"
                dialog.tvNgayHoaDon.text = "Hóa đơn tháng "+date
                dialog.chkThanhToan.isChecked = true

                sum = (hoaDon.gia_thue + hoaDon.gia_dich_vu + (hoaDon.so_dien*3500) + (hoaDon.so_nuoc*25000) - hoaDon.mien_giam)
                Log.d("TAG", "bind: "+sum)
                dialog.tvTongTien.text = sum.toString()+ " Vnd"
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