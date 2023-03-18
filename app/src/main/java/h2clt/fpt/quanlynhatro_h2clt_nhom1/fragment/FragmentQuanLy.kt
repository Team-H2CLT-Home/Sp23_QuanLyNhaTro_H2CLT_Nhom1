package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.TrangChu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_home.*
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.TablayoutQuanlyBinding


class FragmentQuanLy:Fragment() {
    private lateinit var binding:TablayoutQuanlyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TablayoutQuanlyBinding.inflate(inflater,container,false)
        binding.taoHopDong.setOnClickListener {
           val intent = Intent(context,ActivityTaoHopDong::class.java)
            startActivity(intent)
        }
        binding.CocGiuCho.setOnClickListener {
            val intent = Intent(context,ActivityPhongDaCocGiuCho::class.java)
            startActivity(intent)
        }
        binding.TraPhong.setOnClickListener {
            val intent = Intent(context,XuLyPhongActivity::class.java)
            startActivity(intent)
        }
        binding.taoHoaDon.setOnClickListener {
            val intent = Intent(context,FragmentTaoHoaDon::class.java)
            startActivity(intent)
        }
        binding.dsPhongThue.setOnClickListener {
            val intent = Intent(context,ActivityDanhSachPhong::class.java)
            startActivity(intent)
        }
        binding.dsKhachThue.setOnClickListener {
            val intent = Intent(context,ActivityDanhSachNguoiThue::class.java)
            startActivity(intent)
        }
        binding.dsHoaDon.setOnClickListener {
            val intent = Intent(context,ActivityDanhSachHoaDon::class.java)
            startActivity(intent)
        }
        binding.dsHopDong.setOnClickListener {
            val intent = Intent(context,DanhSachHopDongActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}