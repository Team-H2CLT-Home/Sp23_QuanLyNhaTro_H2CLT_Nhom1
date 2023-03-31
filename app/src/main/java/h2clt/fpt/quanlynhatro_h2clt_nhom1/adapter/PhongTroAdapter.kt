package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityChiTietPhong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong

const val MA_PHONG_TRONG_CHI_TIET_PHONG="MA_PHONG_TRONG_CHI_TIET_PHONG"


class PhongTroViewHolder(
    val binding:LayoutItemPhongBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(phong: Phong){
        binding.tvTenPhong.text=phong.ten_phong
        binding.tvGiaThue.text=phong.gia_thue.toString()
        binding.chkTrangThaiPhongDaCoc.isChecked= phong.trang_thai_phong==2
        binding.chkTrangThaiPhongTrong.isChecked=phong.trang_thai_phong==0
        binding.tvGioiHanNguoiO.text = "Tối đa: "+phong.so_nguoi_o.toString()+" người"
        binding.tvSoNguoiHienTai.text = "có "+ NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(phong.ma_phong).size +" người đang ở"
        if(NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(phong.ma_phong)
                .isNotEmpty()
        ){
            binding.chkTrangThaiPhongDaCoc.isChecked = true
            binding.chkTrangThaiPhongTrong.isChecked = false
        }else{
            binding.chkTrangThaiPhongDaCoc.isChecked = false
            binding.chkTrangThaiPhongTrong.isChecked = true
        }
        binding.tvTenPhong.setOnClickListener {
            val phong=PhongDao(binding.root.context).getPhongById(phong.ma_phong)
            val listLoaiDichVu= phong?.let { it1 ->
                LoaiDichVuPhongDao(binding.root.context).getAllInLoaiDichVuByPhong(
                    it1.ma_phong)
            }
            Toast.makeText(binding.root.context, listLoaiDichVu?.get(0)?.ten_loai_dich_vu, Toast.LENGTH_LONG).show()
        }
        binding.lnPhong.setOnClickListener {
            val intent = Intent(binding.root.context, ActivityChiTietPhong::class.java)
            intent.putExtra(MA_PHONG_TRONG_CHI_TIET_PHONG,phong.ma_phong)
            binding.root.context.startActivity(intent)
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
