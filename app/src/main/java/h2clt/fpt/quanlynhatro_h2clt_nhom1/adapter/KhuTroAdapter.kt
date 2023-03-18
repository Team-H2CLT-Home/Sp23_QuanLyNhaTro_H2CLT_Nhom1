package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro

class UserViewHolder(
    val binding: LayoutItemKhuTroBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(khuTro: KhuTro){
        binding.tvTenKhuTro.text ="khu: " + khuTro.ten_khu_tro
        binding.tvSoPhongKhuTro.text ="số phòng: " +khuTro.so_luong_phong.toString()
        binding.tvDiaChiKhuTro.text ="địa chỉ: "+ khuTro.dia_chi
    }
}


class KhuTroAdapter(
    val list:List<KhuTro>
): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutItemKhuTroBinding.inflate(inflater,parent,false)
        return  UserViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val khuTro= list[position]
        holder.bind(khuTro)
    }
    override fun getItemCount(): Int {
        return list.size
    }
}
