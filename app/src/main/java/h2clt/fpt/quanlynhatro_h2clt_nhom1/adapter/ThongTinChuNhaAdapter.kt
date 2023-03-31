package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinChuNhaBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityThongTinCocPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutCaNhanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Admin
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
class ThongTinChuNhaViewHolder(
    val binding: LayoutCaNhanBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(admin: Admin){
        binding.tvTenChuNha.text = admin.ho_ten
        binding.tvSoDienThoai.text = admin.sdt
        binding.tvSoTaiKhoan.text = admin.stk
        binding.tvNgaySinh.text = admin.ngay_sinh
        if(binding.tvSoTaiKhoan.text.equals("")){
            binding.linearSoTaiKhoan.setBackgroundColor(Color.parseColor("#EBEBEB"))
            binding.tvSTK.text = ""
        }
        if(binding.tvNgaySinh.text.equals("")){
            binding.linearNgaySinh.setBackgroundColor(Color.parseColor("#EBEBEB"))
            binding.tvNS.text = ""
        }
    }
}
class ThongTinChuNhaAdapter(val listAdmin: List<Admin>):RecyclerView.Adapter<ThongTinChuNhaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThongTinChuNhaViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutCaNhanBinding.inflate(inflater,parent,false)
        return  ThongTinChuNhaViewHolder(binding)
    }

    override fun getItemCount() = listAdmin.size

    override fun onBindViewHolder(holder: ThongTinChuNhaViewHolder, position: Int) {
        val  admin = listAdmin[position]
        holder.apply {
            bind(admin)
        }
    }

}
