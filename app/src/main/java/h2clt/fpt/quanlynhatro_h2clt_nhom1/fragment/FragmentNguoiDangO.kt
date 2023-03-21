package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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
        listNguoiDung=NguoiDungDao(requireActivity()).getAllInNguoiDungByMaKhu(maKhu)
        Toast.makeText(activity, maKhu, Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(activity, maPhong, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
            dialog.btnLuuThemNguoiDung.setOnClickListener {
                val maNguoiDung = UUID.randomUUID().toString()
                val nguoiDung = NguoiDung(
                    ma_nguoi_dung = maNguoiDung,
                    ho_ten_nguoi_dung = dialog.edHoTenThemNguoiDung.text.toString(),
                    nam_sinh = dialog.edNgaySinhThemNguoiDung.text.toString(),
                    sdt_nguoi_dung = dialog.edSDTThemNguoiDung.text.toString(),
                    cccd = dialog.edCCCDThemNguoiDung.text.toString(),
                    trang_thai_chu_hop_dong = 0,
                    trang_thai_o = 1,
                    ma_phong = maPhong
                )
                val dao = NguoiDungDao(dialog.root.context).insertNguoiDung(nguoiDung)
                if (dao > 0) {
                    Snackbar.make(it, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(it, "Thêm không thành công", Toast.LENGTH_SHORT).show()
                }
                dialog.edHoTenThemNguoiDung.setText("")
                dialog.edSDTThemNguoiDung.setText("")
                dialog.edCCCDThemNguoiDung.setText("")
                dialog.edNgaySinhThemNguoiDung.setText("")
                dialog.edQueQuanThemNguoiDung.setText("")

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
        val nguoiThueAdapter = NguoiThueAdapter(listNguoiDung)
        binding.rcyNguoiDangO.adapter = nguoiThueAdapter
        binding.rcyNguoiDangO.layoutManager = LinearLayoutManager(activity)
    }
    fun thongBaoLoi(loi : String){
        val bundle = androidx.appcompat.app.AlertDialog.Builder(binding.root.context)
        bundle.setTitle("Thông Báo Lỗi")
        bundle.setMessage(loi)
        bundle.setNegativeButton("Hủy", DialogInterface.OnClickListener { dialog, which ->
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
        listNguoiDung=nguoiDungDao.getAllInNguoiDungByMaKhu(maKhu)
        val nguoiThueAdapter=NguoiThueAdapter(listNguoiDung)
        binding.rcyNguoiDangO.adapter=nguoiThueAdapter
        binding.rcyNguoiDangO.layoutManager=LinearLayoutManager(context)
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val nguoiThueAdapter= NguoiThueAdapter()
//        binding.rcyTatCaPhong.adapter=phongTroAdapter
//        binding.rcyTatCaPhong.layoutManager= LinearLayoutManager(context)
//    }
//    private fun laySpinnerPhong(){
//        //khai bao list phong
//        val list = mutableListOf<Phong>()
//        val maPhongSpinner = MaPhongSpinner(binding.root.context,list)
//
//    }

//    public LoaiThuSpinnerAdapter(@NonNull Context context, int resource, List<loaiThu> listLT) {
////        super(context, resource,listLT);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaithu_show,parent,false);
//        TextView tv_item_loaithu_show = convertView.findViewById(R.id.tv_item_loaithu_show);
//        loaiThu lt = this.getItem(position);
//        if(lt!=null){
//            tv_item_loaithu_show.setText(lt.getTenLoaiThu());
//        }
//        return convertView;
//    }
//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
//        TextView tv_selected = convertView.findViewById(R.id.tv_selected);
//        loaiThu lt = this.getItem(position);
//        if(lt!=null){
//            tv_selected.setText(lt.getTenLoaiThu());
//        }
//        return convertView;
//    }
//}
//    LoaiThuSpinnerAdapter adapterLoaiThu = new LoaiThuSpinnerAdapter(dialog.getContext(),R.layout.item_loaithu_show,getListLoaiThu(dialog));
//        sp_loaithu.setAdapter(adapterLoaiThu);
//
//
//    }
//
//    private List<loaiThu> getListLoaiThu(Dialog dialog) {
//        List<loaiThu> list = new ArrayList<>();
//        ltd = new loaiThuDAO(dialog.getContext());
//        list = ltd.getAll();
//        return list;
//    }
}