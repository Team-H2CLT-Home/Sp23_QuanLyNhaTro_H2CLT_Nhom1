package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

class DanhSachPhongViewHolder(
    val binding: LayoutItemPhongBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(phong: Phong){
        binding.tvTenPhong.text = phong.ten_phong.toString()
        binding.tvGiaThue.text = phong.gia_thue.toString()
        binding.chkTrangThaiPhongTrong.isChecked = phong.trang_thai_phong==0
        binding.chkTrangThaiPhongDaCoc.isChecked = false
    }
}
class DanhSachPhongAdapter(
    val list:List<Phong>
):RecyclerView.Adapter<DanhSachPhongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhSachPhongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemPhongBinding.inflate(inflater,parent,false)
        return DanhSachPhongViewHolder(binding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: DanhSachPhongViewHolder, position: Int) {
        val user = list[position]
        holder.bind(user)
    }
}
