package h2clt.fpt.quanlynhatro_h2clt_home.ManHinhChinh.ThongBao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import h2clt.fpt.quanlynhatro_h2clt_home.databinding.TablayoutThongbaoTatcaBinding

class FragmentTBTatCa: Fragment() {
    private lateinit var binding : TablayoutThongbaoTatcaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TablayoutThongbaoTatcaBinding.inflate(layoutInflater)




        return binding.root
    }
}