package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import kotlinx.coroutines.processNextEventInCurrentThread

class PhongTroViewHolder(
    val binding:LayoutItemPhongBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(phong: Phong){
        binding.tvTenPhong.text=phong.ten_phong
        binding.tvGiaThue.text=phong.gia_thue.toString()
        binding.chkTrangThaiPhongDaCoc.isChecked= phong.trang_thai_phong==2
        binding.chkTrangThaiPhongTrong.isChecked=phong.trang_thai_phong==0
        binding.tvTenPhong.setOnClickListener {
            val phong=PhongDao(binding.root.context).getAllInPhongById(phong.ma_phong)
            val listLoaiDichVu= phong?.let { it1 ->
                LoaiDichVuPhongDao(binding.root.context).getAllInLoaiDichVuByPhong(
                    it1.ma_phong)
            }
            Toast.makeText(binding.root.context, listLoaiDichVu?.size.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
class PhongTroAdapter(val listPhong: List<Phong>): RecyclerView.Adapter<PhongTroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhongTroViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = LayoutItemPhongBinding.inflate(inflater,parent,false)
        return  PhongTroViewHolder(binding)
    }

    override fun getItemCount()=listPhong.size

    override fun onBindViewHolder(holder: PhongTroViewHolder, position: Int) {
        val phong=listPhong[position]
        holder.apply {
            bind(phong)
        }
    }
}