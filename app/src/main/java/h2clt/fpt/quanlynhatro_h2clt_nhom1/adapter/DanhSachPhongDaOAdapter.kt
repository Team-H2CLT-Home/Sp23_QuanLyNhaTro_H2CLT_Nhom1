package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.Loading
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.LayoutItemPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment.FragmentTaoHoaDon
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
const val MA_PHONG_HOA_DON_KEY="ma phong de lay hoa don"

class DanhSachPhongDaOViewHolder(
    val binding: LayoutItemPhongBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(phong: Phong){
        if (phong.trang_thai_phong == 0){
            binding.tvTenPhong.text = phong.ten_phong
            binding.tvGiaThue.text = phong.gia_thue.toString()
            binding.chkTrangThaiPhongTrong.isChecked = false
            binding.chkTrangThaiPhongTrong.isEnabled = false
            binding.chkTrangThaiPhongDaCoc.isVisible = false
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
    val list:List<Phong>
):RecyclerView.Adapter<DanhSachPhongDaOViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhSachPhongDaOViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemPhongBinding.inflate(inflater,parent,false)
        return DanhSachPhongDaOViewHolder(binding)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: DanhSachPhongDaOViewHolder, position: Int) {
        val user = list[position]
        holder.bind(user)
    }
}
