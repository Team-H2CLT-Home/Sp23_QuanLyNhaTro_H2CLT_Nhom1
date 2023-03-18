package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.TrangChu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_home.*
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.TablayoutTongquanBinding

class FragmentTongQuan:Fragment() {
    private lateinit var binding: TablayoutTongquanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TablayoutTongquanBinding.inflate(layoutInflater)
        binding.phongTrong.setOnClickListener {
            val intent = Intent(context,PhongTrongActivity::class.java)
            startActivity(intent)
        }
        binding.phongDangChoThue.setOnClickListener {
            val intent = Intent(context,PhongDangThueActivity::class.java)
            startActivity(intent)
        }
        binding.phongSapHetHan.setOnClickListener {
            val intent = Intent(context,ActivityPhongSapHetHopDong::class.java)
            startActivity(intent)
        }
        binding.phongChuaDongTien.setOnClickListener {
            val intent = Intent(context,ActivityPhongChuaDongTien::class.java)
            startActivity(intent)
        }
        binding.phongDaCoc.setOnClickListener {
            val intent = Intent(context,ActivityPhongDaCocGiuCho::class.java)
            startActivity(intent)
        }
        binding.doanhThu.setOnClickListener {
            val intent = Intent(context,DoanhThuActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}