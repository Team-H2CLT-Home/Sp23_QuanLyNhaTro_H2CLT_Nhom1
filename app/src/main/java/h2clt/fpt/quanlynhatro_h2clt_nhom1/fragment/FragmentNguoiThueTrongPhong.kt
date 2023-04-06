package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.ActivityCapNhatKhachThue
import h2clt.fpt.quanlynhatro_h2clt_nhom1.activity.THONG_TIN_PHONG
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentNguoiTrongPhongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung


class FragmentNguoiThueTrongPhong : Fragment() {
    private var maKhu=""
    private var maPhong=""
    var listNguoiDung= listOf<NguoiDung>()
    private lateinit var binding: FragmentNguoiTrongPhongBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNguoiTrongPhongBinding.inflate(layoutInflater)
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val srf2=binding.root.context.getSharedPreferences(THONG_TIN_PHONG, Context.MODE_PRIVATE)
        maPhong=srf2.getString(MA_PHONG_TRONG_CHI_TIET_PHONG,"")!!
        binding = FragmentNguoiTrongPhongBinding.inflate(inflater,container,false)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        listNguoiDung= NguoiDungDao(requireActivity()).getAllInNguoiDangOByMaKhu(maKhu).filter { it.ma_phong==maPhong }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nguoiThueAdapter = NguoiThueAdapter(listNguoiDung,object : KhachThueInterface {
            override fun OnClickKhachThue(pos: Int) {
                val nguoiDung = listNguoiDung[pos]
                val intent = Intent(requireContext(), ActivityCapNhatKhachThue::class.java)
                intent.putExtra("khachThue",nguoiDung)
                startActivity(intent)
            }

        })
        binding.rcyNguoiDangOTrongPhong.adapter = nguoiThueAdapter
        binding.rcyNguoiDangOTrongPhong.layoutManager = LinearLayoutManager(activity)
    }
}

