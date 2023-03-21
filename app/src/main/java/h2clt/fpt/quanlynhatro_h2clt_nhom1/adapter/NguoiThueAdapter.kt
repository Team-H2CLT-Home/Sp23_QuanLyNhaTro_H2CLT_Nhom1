package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemNguoiThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class NguoiThueViewHolder(
    val binding:LayoutItemNguoiThueBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(nguoiDung: NguoiDung){
       binding.tvTenPhong.text =PhongDao(binding.root.context).getTenPhongById(nguoiDung.ma_phong)
        binding.tvSDT.text = nguoiDung.sdt_nguoi_dung.toString()
        binding.tvTenNguoiThue.text = nguoiDung.ho_ten_nguoi_dung.toString()
        binding.edTrangThaiO.isChecked = nguoiDung.trang_thai_o==1
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