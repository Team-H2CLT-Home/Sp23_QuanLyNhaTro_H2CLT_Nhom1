package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogChiTietNguoiThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemNguoiThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class NguoiThueViewHolder(
    val binding:LayoutItemNguoiThueBinding
):RecyclerView.ViewHolder(binding.root){
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(nguoiDung: NguoiDung){

       binding.tvTenPhong.text =PhongDao(binding.root.context).getTenPhongById(nguoiDung.ma_phong)
        binding.tvSDT.text = "SĐT: "+nguoiDung.sdt_nguoi_dung.toString()
        binding.tvTenNguoiThue.text = "Họ tên: " +nguoiDung.ho_ten_nguoi_dung.toString()
        binding.edTrangThaiO.isChecked = nguoiDung.trang_thai_o==1
        if(binding.tvTenNguoiThue.text=="Họ tên: "+NguoiDungDao(binding.root.context).getTenNguoiDangOByMaPhong(nguoiDung.ma_phong)){
            binding.edTrangThaiChuHopDong.isChecked = true
        }else{
            binding.edTrangThaiChuHopDong.isChecked = false
        }
//        if(binding.tvTenNguoiThue.text == NguoiDungDao(binding.root.context).getNguoiDungByTrangThai(nguoiDung.ma_phong,1)){

////  ||binding.tvTenNguoiThue.text=="Họ tên: "+NguoiDungDao(binding.root.context).getTenNguoiDaOByMaPhong(nguoiDung.ma_phong)          Log.d("aaaa", "getNguoiDungByTrangThai: "+NguoiDungDao(binding.root.context).getNguoiDungByTrangThai(nguoiDung.ma_phong,1))
//        Toast.makeText(binding.root.context, NguoiDungDao(binding.root.context).getTenNguoiDaOByMaPhong(nguoiDung.ma_phong), Toast.LENGTH_SHORT).show()
        binding.layoutChuyenChiTietNguoiThue.setOnClickListener {
            val dialog = DialogChiTietNguoiThueBinding.inflate(LayoutInflater.from(binding.root.context))
            val builder = AlertDialog.Builder(binding.root.context).create()
            builder.setView(dialog.root)
            dialog.tvChiTietNguoiDungTenPhong.text = "Tên phòng: "+PhongDao(binding.root.context).getTenPhongById(nguoiDung.ma_phong)
            dialog.tvChiTietNguoiThueHoTen.text = "Họ tên: "+nguoiDung.ho_ten_nguoi_dung
            dialog.tvChiTietNguoiThueSDT.text = "SĐT: "+nguoiDung.sdt_nguoi_dung
            dialog.tvChiTietNguoiThueNgaySinh.text = "Ngày sinh: "+nguoiDung.nam_sinh
            dialog.tvChiTietNguoiThueCCCD.text = "Số CCCD: "+nguoiDung.cccd
            dialog.tvChiTietNguoiThueQueQuan.text = "Quê quán: "+nguoiDung.que_quan
            dialog.btnDongChiTietNguoiThue.setOnClickListener {
                builder.dismiss()
            }

            builder.show()

        }

    }
}
class NguoiThueAdapter(
    val listNguoiDung:List<NguoiDung>
):RecyclerView.Adapter<NguoiThueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NguoiThueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemNguoiThueBinding.inflate(inflater,parent,false)
        return NguoiThueViewHolder(binding)
    }

    override fun getItemCount()=listNguoiDung.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NguoiThueViewHolder, position: Int) {
       val user = listNguoiDung[position]
        holder.bind(user)
    }

}

//class DanhSachPhongAdapter(
//    val list:List<Phong>
//): RecyclerView.Adapter<DanhSachPhongViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhSachPhongViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = LayoutItemPhongBinding.inflate(inflater,parent,false)
//        return DanhSachPhongViewHolder(binding)
//    }
//
//    override fun getItemCount()= list.size
//
//    override fun onBindViewHolder(holder: DanhSachPhongViewHolder, position: Int) {
//        val user = list[position]
//        holder.bind(user)
//    }
//}