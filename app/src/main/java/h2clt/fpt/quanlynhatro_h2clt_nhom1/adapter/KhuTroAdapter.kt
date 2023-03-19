package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro

const val MA_KHU_KEY="ma_khu"
const val TEN_KHU_KEY="ten_khu_tro"
class UserViewHolder(
    val binding: LayoutItemKhuTroBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(khuTro: KhuTro){
        binding.tvTenKhuTro.text ="khu: " + khuTro.ten_khu_tro
        binding.tvSoPhongKhuTro.text ="số phòng: " +khuTro.so_luong_phong.toString()
        binding.tvDiaChiKhuTro.text ="địa chỉ: "+ khuTro.dia_chi
        binding.btnQuanLyKhuTro.setOnClickListener {
            val sharedPreferences=binding.root.context.getSharedPreferences(
                "USER_FILE",
                Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(MA_KHU_KEY,khuTro.ma_khu_tro)
            sharedPreferences.edit().putString(TEN_KHU_KEY,khuTro.ten_khu_tro)
            Toast.makeText(binding.root.context,khuTro.ten_khu_tro, Toast.LENGTH_SHORT).show()
        }
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
