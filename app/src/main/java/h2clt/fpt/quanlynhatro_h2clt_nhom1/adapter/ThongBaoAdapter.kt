package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentNguoiTrongPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemLayoutThongBaoBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.ThongBao

class ThongBaoViewHolder(
    val binding: ItemLayoutThongBaoBinding
):RecyclerView.ViewHolder(binding.root)
class ThongBaoAdapter(
    val list:List<ThongBao>
):RecyclerView.Adapter<ThongBaoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThongBaoViewHolder {
        val inflate=LayoutInflater.from(parent.context)
        val binding=ItemLayoutThongBaoBinding.inflate(inflate, parent, false)
        return ThongBaoViewHolder(binding)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ThongBaoViewHolder, position: Int) {
        val item=list[position]
       holder.apply {
           binding.tvNgayTB.text=item.ngay_thong_bao
           binding.tvTieuDeThongBao.text=item.tieu_de
           binding.tvNoiDung.text=item.noi_dung
       }
    }
}