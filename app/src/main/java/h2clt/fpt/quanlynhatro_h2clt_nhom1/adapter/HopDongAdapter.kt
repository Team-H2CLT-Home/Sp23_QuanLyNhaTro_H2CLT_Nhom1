package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.AlertDialog
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogChiTietHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemDsHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.SimpleDateFormat


class HopDongViewHolder(
    val binding: LayoutItemDsHopDongBinding

): RecyclerView.ViewHolder(binding.root){
    fun bind(hopDong: HopDong){
        // Chuyển từ định dạng yyyy-MM-dd sang dd/MM/yyyy để hiển thị
        // Ngày ở
//        val sdfNgayO = SimpleDateFormat("yyyy-MM-dd")
//        val dateFormatNgayO = DateFormat()
//        val objDateNgayO = sdfNgayO.parse(hopDong.ngay_o)
//        val ngayO = DateFormat.format("dd/MM/yyyy", objDateNgayO) as String
//        //=====================================
//        // Ngày kí hợp đồng
//        val sdfNgayKi = SimpleDateFormat("yyyy-MM-dd")
//        val dateFormatNgayKi = DateFormat()
//        val objDateNgayKi = sdfNgayKi.parse(hopDong.ngay_lap_hop_dong)
//        val ngayKiHopDong = DateFormat.format("dd/MM/yyyy", objDateNgayKi) as String
//        //=====================================
//        // Ngày kết thúc
//        val sdfNgayKetThuc = SimpleDateFormat("yyyy-MM-dd")
//        val dateFormatNgayKetThuc = DateFormat()
//        val objDateNgayKetThuc = sdfNgayKetThuc.parse(hopDong.ngay_hop_dong)
//        val ngayKetThucHopDong = DateFormat.format("dd/MM/yyyy", objDateNgayKetThuc) as String

        //-------------------------------------------------
        binding.tvDanhSachHopDongTenPhong.text=HopDongDao(binding.root.context).getTenPhongByIDHopDong(hopDong.ma_hop_dong)
        binding.tvDanhSachHopDongTenThanhVien.text= "Họ và tên: "+ HopDongDao(binding.root.context).getTenNguoiDungByIDHopDong(hopDong.ma_hop_dong)
        if (hopDong.trang_thai_hop_dong == 1){
            binding.tvDanhSachHopDongTrangThai.text = "Tình trạng hợp đồng: Còn hợp đồng"
        }else{
            binding.tvDanhSachHopDongTrangThai.text = "Tình trạng hợp đồng: Hết hợp đồng"
        }
        binding.tvDanhSachHopDongNgayO.text = chuyenDinhDangNgay(hopDong.ngay_o)
        binding.tvDanhSachHopDongNgayKetThuc.text = chuyenDinhDangNgay(hopDong.ngay_hop_dong)
        binding.tvDanhSachHopDongNgayLap.text = chuyenDinhDangNgay(hopDong.ngay_lap_hop_dong)
        binding.tvChiTietDanhSachHD.setOnClickListener {
              val build = AlertDialog.Builder(binding.root.context).create()
              val dialog = DialogChiTietHopDongBinding.inflate(LayoutInflater.from(binding.root.context))
            build.setView(dialog.root)
            dialog.tvChiTietHDTenPhong.setText("Phòng:"+ HopDongDao(binding.root.context).getTenPhongByIDHopDong(hopDong.ma_hop_dong))
            dialog.tvChiTietHDTenNguoiDung.setText("Họ và tên:"+ HopDongDao(binding.root.context).getTenNguoiDungByIDHopDong(hopDong.ma_hop_dong))
            dialog.tvChiTietHDThoiHan.setText("Thời hạn: "+ hopDong.thoi_han + " tháng")
            dialog.tvChiTietHDNgayO.setText("Ngày ở: "+ chuyenDinhDangNgay(hopDong.ngay_o))
            dialog.tvChiTietHDNgayHopDong.setText("Ngày kết thúc: "+ chuyenDinhDangNgay(hopDong.ngay_hop_dong))
            dialog.tvNgayLapHopDong.setText("Ngày lập hợp đồng: "+ chuyenDinhDangNgay(hopDong.ngay_lap_hop_dong))
            dialog.tvChiTietHDTienCoc.setText("Tiền cọc: "+ hopDong.tien_coc)
            if (hopDong.trang_thai_hop_dong==1){
                dialog.tvChiTietHDTrangThai.setText("Tình trạng hợp đồng: Còn hợp đồng")
            }else{
                dialog.tvChiTietHDTrangThai.setText("Tình trạng hợp đồng: Hết hợp đồng")
            }
            Toast.makeText(dialog.root.context,""+hopDong.ngay_hop_dong,Toast.LENGTH_SHORT).show()
            dialog.btnDongChiTietHopDong.setOnClickListener {

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
class HopDongAdapter (val listHopDong: List<HopDong>): RecyclerView.Adapter<HopDongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HopDongViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutItemDsHopDongBinding.inflate(inflater,parent,false)
        return  HopDongViewHolder(binding)
    }
    override fun getItemCount()=listHopDong.size
    override fun onBindViewHolder(holder: HopDongViewHolder, position: Int) {
        val hopDong=listHopDong[position]
        holder.apply {
            bind(hopDong)
        }
    }
}
