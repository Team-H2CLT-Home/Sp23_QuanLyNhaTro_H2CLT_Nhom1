package h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemListLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ItemListLoaiDichVuBinding.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu

class ListLoaiDichVuAdapter(private val list:List<LoaiDichVu>, val context:Context): BaseAdapter() {
    override fun getCount(): Int=list.size

    override fun getItem(p0: Int): Any =list[p0]
    override fun getItemId(p0: Int)=p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater= LayoutInflater.from(p2?.context)
        val binding= inflate(inflater, p2, false)
        binding.tenLoaiDichVu.text=list[p0].ten_loai_dich_vu
        binding.tvGiaDichVu.text=list[p0].gia_dich_vu.toString()
        binding.swChonLoaiDichVu.setOnCheckedChangeListener { compoundButton, b ->
            if(b)
                Toast.makeText(context, "Đã ấn thay đổi sang true", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Đã ấn thay đổi sang false", Toast.LENGTH_SHORT).show()

        }
        return binding.root
    }
}