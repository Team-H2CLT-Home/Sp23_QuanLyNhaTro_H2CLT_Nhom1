package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityManHinhChinhChuTro
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.KhuTroDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemKhuTroBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.KhuTro

const val MA_KHU_KEY="ma_khu"
const val TEN_KHU_KEY="ten_khu_tro"
const val FILE_NAME="USER_FILE"
class  KhuTroViewHolder(
    val binding: LayoutItemKhuTroBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(khuTro: KhuTro){
        binding.tvTenKhuTro.text ="khu: " + khuTro.ten_khu_tro
        binding.tvSoPhongKhuTro.text ="số phòng: " +khuTro.so_luong_phong.toString()
        binding.tvDiaChiKhuTro.text ="địa chỉ: "+ khuTro.dia_chi
        binding.btnQuanLyKhuTro.setOnClickListener {
            val intent=Intent(binding.root.context, ActivityManHinhChinhChuTro::class.java)
            intent.putExtra(MA_KHU_KEY, khuTro.ma_khu_tro)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            binding.root.context.startActivity(intent)
        }
        binding.btnXoaKhuTro.setOnClickListener {
            val dao=KhuTroDao(binding.root.context)
            if (dao.deleteKhuTro(khuTro)>0) {
                Toast.makeText(binding.root.context, "xóa thành công", Toast.LENGTH_LONG).show()
                val intent=Intent(binding.root.context, ActivityManHinhChinhChuTro::class.java)
                intent.putExtra(MA_KHU_KEY, khuTro.ma_khu_tro)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
                binding.root.context.startActivity(intent)
            }
            else{
                Toast.makeText(binding.root.context, "ko thành công", Toast.LENGTH_LONG).show()

            }

        }
    }
}


class KhuTroAdapter(
    val list:List<KhuTro>
): RecyclerView.Adapter<KhuTroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KhuTroViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutItemKhuTroBinding.inflate(inflater,parent,false)
        return  KhuTroViewHolder(binding)
    }
    override fun onBindViewHolder(holder: KhuTroViewHolder, position: Int) {
        val khuTro= list[position]
        holder.bind(khuTro)
    }
    override fun getItemCount(): Int {
        return list.size
    }

}
