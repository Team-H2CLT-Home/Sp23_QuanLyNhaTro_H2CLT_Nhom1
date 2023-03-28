package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityKetThucHopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HopDongPhongHetHanAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HopDongPhongSapHetHanAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogGiaHanHdBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHopDongSapHetHanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class FragmentHopDongSapHetHan : Fragment() {
    private lateinit var binding: FragmentHopDongSapHetHanBinding
    var listHopDongSapHetHan = listOf<HopDong>()
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var maKhu=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHopDongSapHetHanBinding.inflate(LayoutInflater.from(context))

        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        reLoadList()
        //onResume()
        return binding.root
    }


    fun reLoadList(){
        listHopDongSapHetHan = HopDongDao(requireContext()).getHopDongSapHetHanByMaKhu(maKhu,2,1)
        //listHopDong=HopDongDao(this@ActivityDanhSachHopDong).getAllInHopDong()
        val hopDongAdapter= HopDongPhongSapHetHanAdapter(listHopDongSapHetHan,this)
        binding.rcySapHetHan.adapter = hopDongAdapter
        binding.rcySapHetHan.layoutManager= LinearLayoutManager(requireContext())
        hopDongAdapter.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()
        reLoadList()
    }

    fun giaHanHopDong(hopDong: HopDong) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(binding.root.context).create()
        val dialog  = DialogGiaHanHdBinding.inflate(LayoutInflater.from(binding.root.context))
        val tenPhong = PhongDao(binding.root.context).getTenPhongById(hopDong.ma_phong)
        if (tenPhong != null) {
            dialog.tvTenPhongGiaHanHD.setText(tenPhong)
        }
        dialog.edThoiHanCuGiaHan.setText(""+hopDong.thoi_han)
        dialog.edNgayKetThucHopDongCu.setText(""+chuyenDinhDangNgay(hopDong.ngay_hop_dong))
        dialog.edThoiHanMoiGiaHan.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) {
                val stringOldDate = chuyenDinhDangNgay(hopDong.ngay_hop_dong)
                //val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                val dateFormat: java.text.DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val newDate = dateFormat.parse(stringOldDate)
                val calendar = Calendar.getInstance()
                if (newDate != null) {
                    calendar.time = newDate
                }
                val month = calendar.get(Calendar.MONTH) + text.toString().toInt()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val year = calendar.get(Calendar.YEAR)
                val c1 = GregorianCalendar(year, month, day)
                dialog.edNgayKetThucHopDongMoi.setText(simpleDateFormat.format(c1!!.time))
            }
        }
        dialog.btnLuuGiaHan.setOnClickListener {
            updateHopDong(hopDong, dialog, builder)
        }

        builder.setView(dialog.root)
        builder.show()
    }

    private fun updateHopDong(
        hopDong: HopDong,
        dialog: DialogGiaHanHdBinding,
        builder: androidx.appcompat.app.AlertDialog
    ) {

        try {
            // Kiểm tra dữ liệu nhập vào
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dateFormat = android.text.format.DateFormat()
            val objDate = sdf.parse(dialog.edNgayKetThucHopDongMoi.getText().toString().trim { it <= ' ' })
            val ngayBatDauO =
                android.text.format.DateFormat.format("dd/MM/yyyy", objDate) as String
            dialog.edNgayKetThucHopDongMoi.setText(ngayBatDauO)
        } catch (ex: Exception) {
            Toast.makeText(
                binding.root.context,
                "Ngày kết thúc không đúng định dạng(dd/MM/yyyy)",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val hopDongNew = HopDong(
            ma_hop_dong = hopDong.ma_hop_dong,
            ma_phong = hopDong.ma_phong,
            ma_nguoi_dung = hopDong.ma_nguoi_dung,
            thoi_han = dialog.edThoiHanMoiGiaHan.text.toString().toInt(),
            ngay_o = hopDong.ngay_o,
            ngay_hop_dong = chuyenDinhDang(dialog.edNgayKetThucHopDongMoi.text),
            tien_coc = hopDong.tien_coc,
            anh_hop_dong = hopDong.anh_hop_dong,
            trang_thai_hop_dong = 1,
            hieu_luc_hop_dong = hopDong.hieu_luc_hop_dong,
            ngay_lap_hop_dong = hopDong.ngay_lap_hop_dong
        )
        var update = HopDongDao(binding.root.context).updateHopDong(hopDongNew)
        if (update>0){
            Toast.makeText(
                binding.root.context,
                "Gia hạn thành công",
                Toast.LENGTH_SHORT
            ).show()
            reLoadList()
            builder.dismiss()

        }
    }

    private fun chuyenDinhDang(text: Editable?): String {
        var ngay_chuan_dinh_dang = ""
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dateFormat = android.text.format.DateFormat()
            val objDate = sdf.parse(text.toString().trim { it <= ' ' })
            ngay_chuan_dinh_dang =
                android.text.format.DateFormat.format("yyyy-MM-dd", objDate) as String
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ngay_chuan_dinh_dang
    }

    private fun chuyenDinhDangNgay(ngay: String):String {
        val sdfNgay = SimpleDateFormat("yyyy-MM-dd")
        val dateFormatNgayO = DateFormat()
        val objDateNgayO = sdfNgay.parse(ngay)
        val ngay = DateFormat.format("dd/MM/yyyy", objDateNgayO) as String
        return ngay
    }

    fun ketThucHopDong(hopDong: HopDong) {
        val intent = Intent(context, ActivityKetThucHopDong::class.java)
        val bundle:Bundle
        //bundle.putString("")
        intent.putExtra("hopDong",hopDong)
        startActivity(intent)
    }


}