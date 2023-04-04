package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.Telephony
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogHoaDonChiTietBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemHoaDonBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.text.SimpleDateFormat
import java.util.*

class HoaDonDaThanhToanViewHolder(
    var binding: LayoutItemHoaDonBinding

):RecyclerView.ViewHolder(binding.root){
    fun bind(hoaDon: HoaDon){


        if (hoaDon.trang_thai_hoa_don==1 ){
            binding.tvTrangThaiHoaDon.isChecked = true
            binding.tvTrangThaiHoaDon.isClickable = false
            val phong = PhongDao(binding.root.context).getPhongById(hoaDon.ma_phong)
            binding.tvTenPhong.text = phong?.ten_phong
            // tính tổng
            val chuHopDong= phong?.let { phong ->
                NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(phong.ma_phong).find { it.trang_thai_chu_hop_dong==1 }
            }

            binding.tvTong.text = hoaDon.tong.toString()
            binding.tvTong.setTextColor(Color.argb(200,0,200,0))
            binding.tvDaThu.text =  hoaDon.tong.toString()
            binding.tvDaThu.setTextColor(Color.argb(200,0,200,0))

            val dateFormat: java.text.DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val newDate = dateFormat.parse(hoaDon.ngay_tao_hoa_don)
            val calendar = Calendar.getInstance()
            if (newDate != null) {
                calendar.time = newDate
            }
            val month = calendar.get(Calendar.MONTH)+1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val year = calendar.get(Calendar.YEAR)

            binding.tvThang.setText("T${month.toString()}")
            binding.tvNam.setText(year.toString())


            if (chuHopDong!=null) {
                val sdt_ND = chuHopDong.sdt_nguoi_dung
                val message_ND =
                    "Thông báo hóa đơn tháng ${chuyenNgay(hoaDon.ngay_tao_hoa_don)} tổng tiền là ${hoaDon.tong}"
                binding.thongBaoHoaDon.setOnClickListener {
                    //   Toast.makeText(binding.root.context,"Sdt ${sdt_ND} và ${message_ND}",Toast.LENGTH_SHORT).show()

                    nhanTinHD(sdt_ND.toString(), message_ND, binding.root.context)
                }
                binding.goiDienHoaDon.setOnClickListener {
                    goiDienHD(sdt_ND, binding.root.context)
                }
            }


            binding.layoutChuyenChiTietHoaDon.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(binding.root.context)
                val dialog = DialogHoaDonChiTietBinding.inflate(LayoutInflater.from(binding.root.context))
                bottomSheetDialog.setContentView(dialog.root)
//                Toast.makeText(binding.root.context,sdt_ND.toString(),Toast.LENGTH_SHORT).show()

                dialog.tvTenPhong.text = phong?.ten_phong
                dialog.tvNgay.text = chuyenNgay(hoaDon.ngay_tao_hoa_don)
                dialog.tvTienPhong.text = hoaDon.gia_thue.toString() + " Vnd"
                dialog.tvGiaDichVu.text = hoaDon.gia_dich_vu.toString() +" Vnd"
                dialog.tvSoDien.text = hoaDon.so_dien.toString() + " Số"
                dialog.tvKhoiNuoc.text = hoaDon.so_nuoc.toString() + " Khối"
                dialog.tvTienMienGiam.text = hoaDon.mien_giam.toString() + " Vnd"
                dialog.tvNgayHoaDon.text = "Hóa đơn tháng "+ chuyenNgay(hoaDon.ngay_tao_hoa_don)
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
fun chuyenNgay(ngay : String ):String{
    val sdfNgay = SimpleDateFormat("yyyy-MM-dd")
    val objDate = sdfNgay.parse(ngay)
    val ngay =  DateFormat.format("MM-yyyy",objDate) as String
    return ngay
}

fun nhanTinHD(sdt:String, message:String, context: Context){

    val uri = Uri.parse("smsto:+$sdt")
    val intent = Intent(Intent.ACTION_SENDTO, uri)
    with(intent) {
        putExtra("address", "+$sdt")
        putExtra("sms_body", message)
    }
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
            val defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context)
            if (defaultSmsPackageName != null) intent.setPackage(defaultSmsPackageName)
            context.startActivity(intent)
        }
        else -> context.startActivity(intent)
    }
}
fun goiDienHD(sdt:String, context:Context){

    val dialIntent = Intent(Intent.ACTION_DIAL)
    dialIntent.data = Uri.parse("tel:$sdt")
    context.startActivity(dialIntent)
}

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