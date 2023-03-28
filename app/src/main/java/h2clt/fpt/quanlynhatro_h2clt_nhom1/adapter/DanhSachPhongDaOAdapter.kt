package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.Loading
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongDangOBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment.FragmentTaoHoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
const val MA_PHONG_HOA_DON_KEY="ma phong de lay hoa don"

class DanhSachPhongDaOViewHolder(
    val binding: LayoutItemPhongDangOBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(phong: NguoiDung){
        if (phong.trang_thai_o == 1){
            val maPhong = PhongDao(binding.root.context).getPhongById(phong.ma_phong)
            binding.tvTenPhong.text = maPhong?.ten_phong
            binding.tvGiaThue.text = maPhong?.gia_thue.toString()
            binding.chkTrangThaiPhongTrong.isChecked = false
            binding.chkTrangThaiPhongTrong.isEnabled = false
            binding.linnerLayoutItemPhong.setOnClickListener {
              manHinhHoaDon(binding.root.context,phong.ma_phong)
            }
        }else{
            binding.linnerLayoutItemPhong.isVisible = false
        }
    }
}
fun manHinhHoaDon(context: Context, id:String){
        val intent = Intent(context,FragmentTaoHoaDon::class.java)
        intent.apply {
            putExtra(MA_PHONG_HOA_DON_KEY, id)
        }
        context.startActivity(intent)
}
class DanhSachPhongDaOAdapter(
    val list:List<NguoiDung>
):RecyclerView.Adapter<DanhSachPhongDaOViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhSachPhongDaOViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemPhongDangOBinding.inflate(inflater,parent,false)
        return DanhSachPhongDaOViewHolder(binding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: DanhSachPhongDaOViewHolder, position: Int) {
        val user = list[position]
        holder.bind(user)
    }
}
