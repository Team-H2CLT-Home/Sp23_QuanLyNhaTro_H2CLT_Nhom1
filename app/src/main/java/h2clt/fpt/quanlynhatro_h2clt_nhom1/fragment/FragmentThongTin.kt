package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.THONG_TIN_PHONG
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.LoaiDichVuPhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemLoaiDichVuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentThongTinBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.LoaiDichVu
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong


class FragmentThongTin : Fragment() {
    private lateinit var binding:FragmentThongTinBinding
    private lateinit var phongDao:PhongDao
    private lateinit var context: Context
    private var maPhong=""
    private var maKhu=""
    private lateinit var phong:Phong
    private lateinit var listLoaiDichVu:List<LoaiDichVu>
    private lateinit var listDichTrongPhong:List<LoaiDichVu>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentThongTinBinding.inflate(layoutInflater)
        val srf=binding.root.context.getSharedPreferences(THONG_TIN_PHONG, Context.MODE_PRIVATE)
        val srf1=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu=srf1.getString(MA_KHU_KEY,"")!!
        maPhong=srf.getString(MA_PHONG_TRONG_CHI_TIET_PHONG,"")!!
        context=binding.root.context
        phongDao= PhongDao(context)
        phong=phongDao.getPhongById(maPhong)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvChiTietTenPhong.text = phong.ten_phong
        binding.tvChiTietDienTich.text=phong.dien_tich.toString()
        binding.tvChiTietGiaThue.text=phong.gia_thue.toString()
        binding.tvChiTietSoNguoi.text=phong.so_nguoi_o.toString()
        listDichTrongPhong=LoaiDichVuPhongDao(context).getAllInLoaiDichVuByPhong(maPhong)
        listLoaiDichVu=LoaiDichVuPhongDao(context).getAllInLoaiDichVuByKhuTro(maKhu)
        val listDichVuTrongPhongAdapter= ListDichVuTrongPhongAdapter(listDichTrongPhong, context)
        binding.rcvLoaiDichVu.adapter=listDichVuTrongPhongAdapter
        binding.rcvLoaiDichVu.layoutManager= LinearLayoutManager(context)

        //thêm hoặc xóa dịch vụ ở đây
        binding.btnThemHoacXoaDichVu.setOnClickListener {
            onPause()
            val build = AlertDialog.Builder(context).create()
            val dialog = DialogThemLoaiDichVuBinding.inflate(LayoutInflater.from(context))
            val listLoaiDichVuAdapter= ListLoaiDichVuAdapter(listLoaiDichVu,
                context, maPhong)
            dialog.listLoaiDichVu.adapter=listLoaiDichVuAdapter
            dialog.btnHuyLoaiDV.setOnClickListener {
                onResume()
                build.dismiss()
            }
            build.setView(dialog.root)
            build.show()
        }
        binding.tvChiTietXoaPhong.setOnClickListener {
            phongDao.xoaPhongById(maPhong)
            LoaiDichVuPhongDao(context).xoaLoaiDichVuByMaPhong(maPhong)
            activity?.finish()
        }

    }

    override fun onResume() {
        super.onResume()
        listDichTrongPhong=LoaiDichVuPhongDao(context).getAllInLoaiDichVuByPhong(maPhong)
        val listDichVuTrongPhongAdapter= ListDichVuTrongPhongAdapter(listDichTrongPhong, context)
        binding.rcvLoaiDichVu.adapter=listDichVuTrongPhongAdapter
        binding.rcvLoaiDichVu.layoutManager= LinearLayoutManager(context)

    }

}