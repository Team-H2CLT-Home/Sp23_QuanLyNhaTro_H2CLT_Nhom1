package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemLayoutLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu

class LoaiDichVuViewHolder(
    val binding:ItemLayoutLoaiDichVuBinding
):RecyclerView.ViewHolder(binding.root)

class ListDichVuTrongPhongAdapter(
    val list:List<LoaiDichVu>,
    val context:Context
): RecyclerView.Adapter<LoaiDichVuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoaiDichVuViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemLayoutLoaiDichVuBinding.inflate(inflater, parent, false)
        return LoaiDichVuViewHolder(binding)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: LoaiDichVuViewHolder, position: Int) {
        val item=list[position]
        val gia=when(item.trang_thai_loai_dich_vu){
            0 -> "Miễn Phí"
            1 -> "${item.gia_dich_vu} Đồng/Số"
            2 -> "${item.gia_dich_vu} Đồng/người"
            3 -> "${item.gia_dich_vu} Đồng/Phòng"
            else -> {""}
        }
        holder.binding.tenLoaiDichVu.text=item.ten_loai_dich_vu
        holder.binding.tvGiaDichVu.text=gia
    }
}