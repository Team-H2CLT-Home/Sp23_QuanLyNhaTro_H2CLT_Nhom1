package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.DialogInterface
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
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
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
        val soNguoiO=NguoiDungDao(binding.root.context).getNguoiDungByMaPhong(maPhong).size
        binding.edChiTietTenPhong.setText(phong.ten_phong)
        binding.edChiTietDienTich.setText(phong.dien_tich.toString())
        binding.edGiaThue.setText(phong.gia_thue.toString())
        binding.tvSoNguoiHienTai.setText(soNguoiO.toString())
        binding.edSoNguoiOToiDa.setText(phong.so_nguoi_o.toString())
        listDichTrongPhong=LoaiDichVuPhongDao(context).getAllInLoaiDichVuByPhong(maPhong)
        binding.edSoDichVu.setText(listDichTrongPhong.size.toString())
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
        binding.btnXoaPhong.setOnClickListener {
            val soNguoiTrongPhong=NguoiDungDao(binding.root.context).getListNguoiDungByMaPhong(maPhong).size
            if(soNguoiTrongPhong<=0) {
                phongDao.xoaPhongById(maPhong)
                LoaiDichVuPhongDao(context).xoaLoaiDichVuByMaPhong(maPhong)
                activity?.finish()
            }
            else{
                thongBaoLoi("Không thể xoá phòng có thông tin!!!")
            }
        }
        binding.btnCapNhapPhong.setOnClickListener {
            val tenPhong=binding.edChiTietTenPhong.text.toString()
            val dienTich=binding.edChiTietDienTich.text.toString().toInt()
            val giaThue=binding.edGiaThue.text.toString().toLong()
            val soNguoiOToiDa=binding.edSoNguoiOToiDa.text.toString().toInt()
            val phong=Phong(
                ma_phong = maPhong,
                ma_khu = maKhu,
                dien_tich = dienTich,
                gia_thue = giaThue,
                ten_phong = tenPhong,
                so_nguoi_o = soNguoiOToiDa,
                trang_thai_phong = 1
            )
            PhongDao(binding.root.context).updatePhong(phong)
            if ( PhongDao(binding.root.context).updatePhong(phong)>0){
                activity?.finish()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        listDichTrongPhong=LoaiDichVuPhongDao(context).getAllInLoaiDichVuByPhong(maPhong)
        val listDichVuTrongPhongAdapter= ListDichVuTrongPhongAdapter(listDichTrongPhong, context)
        binding.rcvLoaiDichVu.adapter=listDichVuTrongPhongAdapter
        binding.rcvLoaiDichVu.layoutManager= LinearLayoutManager(context)

    }
    fun thongBaoLoi(loi : String){
        val bundle = AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
}