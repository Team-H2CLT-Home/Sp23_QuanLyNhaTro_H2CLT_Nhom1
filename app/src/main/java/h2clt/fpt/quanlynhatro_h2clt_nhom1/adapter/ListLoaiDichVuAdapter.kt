package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemListLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemListLoaiDichVuBinding.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import java.util.UUID

val listChon= mutableListOf<Any?>()
var list= listOf<LoaiDichVu>()
var item1:Any?=null
class ListLoaiDichVuAdapter(
    private val list:List<LoaiDichVu>,
    val context:Context,
    val maPhong:String
    ): BaseAdapter()
{
    override fun getCount(): Int=list.size

    override fun getItem(p0: Int): Any =list[p0]
    override fun getItemId(p0: Int)=p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val loaiDichVuPhongDao=LoaiDichVuPhongDao(context)
        val checkList=loaiDichVuPhongDao.getAllInLoaiDichVuByPhong(maPhong)
        var item=list[p0]
        val inflater= LayoutInflater.from(p2?.context)
        val binding= inflate(inflater, p2, false)
        val gia=when(item.trang_thai_loai_dich_vu){
            0 -> "Miễn Phí"
            1 -> "${item.gia_dich_vu} Đồng/Số"
            2 -> "${item.gia_dich_vu} Đồng/người"
            3 -> "${item.gia_dich_vu} Đồng/Phòng"
            else -> {""}
        }
        binding.tenLoaiDichVu.text=item.ten_loai_dich_vu
        binding.tvGiaDichVu.text=gia
        if (item.ten_loai_dich_vu in checkList.map { it.ten_loai_dich_vu }){
            binding.swChonLoaiDichVu.isChecked=true
        }
        binding.swChonLoaiDichVu.setOnCheckedChangeListener { _, b ->
            item1=item.copy(ma_phong =maPhong, ma_loai_dich_vu = UUID.randomUUID().toString())
            if(b)
                 loaiDichVuPhongDao.insertLoaiDichVuPhong(item1 as LoaiDichVu)
            else
                loaiDichVuPhongDao.xoaLoaiDichVuByTenVaMaPhong(item1 as LoaiDichVu)
        }

        return binding.root
    }
}