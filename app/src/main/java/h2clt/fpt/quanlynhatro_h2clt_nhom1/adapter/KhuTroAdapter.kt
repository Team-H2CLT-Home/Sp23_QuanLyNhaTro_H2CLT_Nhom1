package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
        val pre = binding.root.context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
        val maKhu = pre.getString(MA_KHU_KEY,"")
        binding.tvTenKhuTro.text ="Khu: " + khuTro.ten_khu_tro
        binding.tvSoPhongKhuTro.text ="Số phòng: " +khuTro.so_luong_phong.toString()
        binding.tvDiaChiKhuTro.text ="Địa chỉ: "+ khuTro.dia_chi
        binding.btnQuanLyKhuTro.setOnClickListener {
            val intent=Intent(binding.root.context, ActivityManHinhChinhChuTro::class.java)
            intent.putExtra(MA_KHU_KEY, khuTro.ma_khu_tro)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags=Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            binding.root.context.startActivity(intent)
        }
        if (maKhu==khuTro.ma_khu_tro){
            binding.chkTrangThaiKhuTro.isVisible = true
            binding.chkTrangThaiKhuTro.isChecked = true
        }
        binding.btnXoaKhuTro.setOnClickListener {
            val dao=KhuTroDao(binding.root.context)
            if(dao.deleteKhuTro(khuTro)>0){
                thongBaoThanhCong("Bạn đã xóa thành công !!!")
            }else{
                thongBaoLoi("Bạn xóa không thành công !!!")
            }
        }
    }

    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo Lỗi !!!")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    fun thongBaoThanhCong(loi: String){
        val bundle = AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo thành công !!!")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val intent=Intent(binding.root.context, ActivityManHinhChinhChuTro::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
            binding.root.context.startActivity(intent)
        })
        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
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
