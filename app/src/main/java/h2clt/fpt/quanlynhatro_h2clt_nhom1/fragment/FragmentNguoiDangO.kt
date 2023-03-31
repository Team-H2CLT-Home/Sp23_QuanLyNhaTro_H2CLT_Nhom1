package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCapNhatHopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCapNhatKhachThue
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityDangNhap
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemKhachThueBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentNguoiDangOBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.Phong
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class FragmentNguoiDangO: Fragment() {
     private lateinit var binding: FragmentNguoiDangOBinding
     private var maKhu=""
    private var maPhong=""
    var listNguoiDung= listOf<NguoiDung>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNguoiDangOBinding.inflate(LayoutInflater.from(context))

        // Inflate the layout for this fragment
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
//        listNguoiDung=NguoiDungDao(requireActivity()).getAllInNguoiDungByMaKhu(maKhu)
        listNguoiDung=NguoiDungDao(requireActivity()).getAllInNguoiDangOByMaKhu(maKhu)
//        Toast.makeText(activity, maKhu, Toast.LENGTH_SHORT).show()
        //search
//        binding.rcyNguoiDangO.setOn
        binding.searchTenPhong.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var list = NguoiDungDao(binding.root.context).getAllInNguoiDungByMaKhu(maKhu)
                if(newText!=null){
                    list= NguoiDung.timkiemUser(list,newText)
                }
                val adapter = NguoiThueAdapter(list,object :KhachThueInterface{
                    override fun OnClickKhachThue(pos: Int) {
                        val nguoiDung = listNguoiDung.get(pos)
                        val intent = Intent(requireContext(), ActivityCapNhatKhachThue::class.java)
                        intent.putExtra("khachThue",nguoiDung)
                        startActivity(intent)
                    }

                })
                binding.rcyNguoiDangO.adapter = adapter
                return false

            }

        })

        //search
        binding.imgAddNguoiThue.setOnClickListener {
            val dialog=DialogThemKhachThueBinding.inflate(LayoutInflater.from(activity))
            val build=AlertDialog.Builder(activity).create()
            val listPhong=PhongDao(requireActivity()).getAllInPhongByMaKhu(maKhu)
            val spinner=MaPhongSpinner(requireActivity(), listPhong)
            dialog.spinnerThemNguoiDung.adapter=spinner
            dialog.spinnerThemNguoiDung.onItemSelectedListener=object : OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    maPhong=listPhong[position].ma_phong
//                    Toast.makeText(activity, maPhong, Toast.LENGTH_SHORT).show()
                    Toast.makeText(activity, "Tên phòng: "+listPhong[position].ten_phong, Toast.LENGTH_SHORT).show()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            dialog.btnLuuThemNguoiDung.setOnClickListener {
                if(dialog.edHoTenThemNguoiDung.text.toString().isNotBlank()&&dialog.edNgaySinhThemNguoiDung.text.toString().isNotBlank()
                    &&dialog.edSDTThemNguoiDung.text.toString().isNotBlank()&&dialog.edCCCDThemNguoiDung.text.toString().isNotBlank()
                    &&dialog.edQueQuanThemNguoiDung.text.toString().isNotBlank()){
                    //check ho ten
//                    val hoTen =  dialog.edHoTenThemNguoiDung.text.toString()
//                    val kiemTraHoTen = ("^[a-zA-Z]")
//                    val pattern3 = Pattern.compile(kiemTraHoTen)
//                    val matcher3 = pattern3.matcher(hoTen)
//                    if(!matcher3.find()){
//                        thongBaoLoi("Nhập lại họ tên khách thuê")
//                        return@setOnClickListener
//
//                    }
//                    ///kiem tra ngay
//                    try{
//                        val spf = SimpleDateFormat("dd/MM/yyyy")
//                        val objDate:Date = spf.parse(dialog.edNgaySinhThemNguoiDung.text.toString().trim())
//                        val dateFormat = android.text.format.DateFormat.format("dd-MM-yyyy",objDate)
//                        val ngay: String = dateFormat.toString()
////                        val nguoiDung:NguoiDung
//////                        nguoiDung.nam_sinh(ngay)
//
//                    }catch (e:java.lang.Exception){
//                        thongBaoLoi("Ngày sinh không đúng định dạng dd/MM/yyyy")
//                        return@setOnClickListener
//                    }
//                    //check sdt
//                    val sdt =  dialog.edSDTThemNguoiDung.text.toString()
//                    val kiemTraSDT = ("^(0)+[0-9]{9}$")
//                    val pattern1 = Pattern.compile(kiemTraSDT)
//                    val matcher1 = pattern1.matcher(sdt)
//                    if(!matcher1.find()){
//                        thongBaoLoi("Nhập lại số điện thoại khách thuê")
//                        return@setOnClickListener
//
//                    }
//                    //check cccd
//                    val cccd =  dialog.edCCCDThemNguoiDung.text.toString()
//                    val kiemTraCCCD = ("^(0)+[0-9]{11}$")
//                    val pattern2 = Pattern.compile(kiemTraCCCD)
//                    val matcher2 = pattern2.matcher(cccd)
//                    if(!matcher2.find()){
//                        thongBaoLoi("Nhập lại số cccd khách thuê")
//                        return@setOnClickListener
//
//                    }
                    val listNguoiDungByMaPhong = NguoiDungDao(requireActivity()).getListNguoiDungByMaPhong(maPhong)
                    val soNguoiO = NguoiDungDao(requireActivity()).getSoNguoiOByMaPhong(maPhong)
//                    Toast.makeText(binding.root.context, soNguoiO, Toast.LENGTH_SHORT).show()
                    if(listNguoiDungByMaPhong.size<soNguoiO){
                        val maNguoiDung = UUID.randomUUID().toString()
                        val nguoiDung = NguoiDung(
                            ma_nguoi_dung = maNguoiDung,
                            ho_ten_nguoi_dung = dialog.edHoTenThemNguoiDung.text.toString(),
                            nam_sinh = dialog.edNgaySinhThemNguoiDung.text.toString(),
                            sdt_nguoi_dung = dialog.edSDTThemNguoiDung.text.toString(),
                            que_quan = dialog.edQueQuanThemNguoiDung.text.toString(),
                            cccd = dialog.edCCCDThemNguoiDung.text.toString(),
                            trang_thai_chu_hop_dong = 0,
                            trang_thai_o = 1,
                            ma_phong = maPhong
                        )
                        val dao = NguoiDungDao(dialog.root.context).insertNguoiDung(nguoiDung)
                        if (dao > 0) {
                            PhongDao(binding.root.context).updateTrangThaiPhongThanhDangO(maPhong)
//                            thongBaoThanhCong("Thêm người dùng thành công")
                            Snackbar.make(it, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show()
                        } else {
                            Snackbar.make(it, "Thêm không thành công", Toast.LENGTH_SHORT).show()
//                            thongBaoLoi("Thêm người dùng không thành công")
                        }
                        ///
//                        dialog.edHoTenThemNguoiDung.setText("")
//                        dialog.edSDTThemNguoiDung.setText("")
//                        dialog.edCCCDThemNguoiDung.setText("")
//                        dialog.edNgaySinhThemNguoiDung.setText("")
//                        dialog.edQueQuanThemNguoiDung.setText("")
                        build.dismiss()
                        onResume()
                    }else{
                        thongBaoLoi("Phòng đã đủ người")
                    }


                }else{
                    thongBaoLoi("Dữ liệu không được để trống!!!")
                }


            }
            dialog.btnHuyThemNguoiDung.setOnClickListener {
                build.dismiss()
                onResume()
            }

            build.setView(dialog.root)
            build.show()
            onPause()
        }

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nguoiThueAdapter = NguoiThueAdapter(listNguoiDung,object :KhachThueInterface{
            override fun OnClickKhachThue(pos: Int) {
                val nguoiDung = listNguoiDung.get(pos)
                val intent = Intent(requireContext(), ActivityCapNhatKhachThue::class.java)
                intent.putExtra("khachThue",nguoiDung)
                startActivity(intent)
            }

        })
        binding.rcyNguoiDangO.adapter = nguoiThueAdapter
        binding.rcyNguoiDangO.layoutManager = LinearLayoutManager(activity)
    }
    fun thongBaoLoi(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        bundle.show()
    }
    override fun onResume() {
        super.onResume()
        reload()
    }
    private fun reload(){
        val nguoiDungDao= activity?.let { NguoiDungDao(it) }!!
//        listNguoiDung=nguoiDungDao.getAllInNguoiDungByMaKhu(maKhu)
        listNguoiDung=nguoiDungDao.getAllInNguoiDangOByMaKhu(maKhu)
        val nguoiThueAdapter=NguoiThueAdapter(listNguoiDung,object :KhachThueInterface{
            override fun OnClickKhachThue(pos: Int) {
                val nguoiDung = listNguoiDung.get(pos)
                val intent = Intent(requireContext(), ActivityCapNhatKhachThue::class.java)
                intent.putExtra("khachThue",nguoiDung)
                startActivity(intent)
            }

        })
        binding.rcyNguoiDangO.adapter=nguoiThueAdapter
        binding.rcyNguoiDangO.layoutManager=LinearLayoutManager(context)
    }
    fun thongBaoThanhCong(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo")
        bundle.setMessage(loi)
        bundle.setNegativeButton("OK", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
//            onResume()

        })
//        bundle.setPositiveButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
//            dialog.cancel()
//        })
        bundle.show()
    }
//    private fun updateNDTrongPhong(ma:Phong):Int {
//        val update = NguoiDungDao(binding.root.context).updateNguoiDung(nguoiDungNew)
//        return update
//    }

}