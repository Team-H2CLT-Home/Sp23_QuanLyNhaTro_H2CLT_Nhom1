package h2clt.fpt.quanlynhatro_h2clt_nhom1.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.*
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.FragmentHoaDonDathanhToanBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*

class FragmentHoaDonDathanhToan : Fragment() {
    private lateinit var binding: FragmentHoaDonDathanhToanBinding
    private var maKhu = ""
    private var list = listOf<HoaDon>()
    private var count=0
    var currentYear=""
    var curentMonth=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoaDonDathanhToanBinding.inflate(LayoutInflater.from(context))


        val sfp = SimpleDateFormat("yyyy-MM-dd")
        val data = sfp.format(Date())

        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val newDate = dateFormat.parse(data)
        val calendar = Calendar.getInstance()
        if (newDate != null) {
            calendar.time = newDate
        }
        val thang = (calendar.get(Calendar.MONTH)+1).toString()
        currentYear = calendar.get(Calendar.YEAR).toString()
        curentMonth= if(thang.length==1) "0$thang" else thang
        binding.tvThangDSHD.text ="Tháng ${thang},${currentYear}"
        binding.icPreviousDSHD.setOnClickListener {
            count-=1
            val sfp = SimpleDateFormat("yyyy-MM-dd")
            val data = sfp.format(Date())
            val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val newDate = dateFormat.parse(data)
            val dFormat=SimpleDateFormat("yyyy-MM")
            val calendar = Calendar.getInstance()
            if (newDate != null) {
                calendar.time = newDate
            }
            val thang = calendar.get(Calendar.MONTH)+count
            val year = calendar.get(Calendar.YEAR)
            val c1 = GregorianCalendar(year, thang, 1)
            val m=(c1.get(Calendar.MONTH)+1).toString()
            val y=(c1.get(Calendar.YEAR)).toString()
            val newM= if(m.length==1) "0$m" else m
            hienThiThangCu(binding.root.context,maKhu,"$y-$newM")
            binding.tvThangDSHD.text ="Tháng ${m},${y}"

        }

        binding.icNextDSHD.setOnClickListener {
            count+=1
            val sfp = SimpleDateFormat("yyyy-MM-dd")
            val data = sfp.format(Date())
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dFormat=SimpleDateFormat("yyyy-MM")
            val newDate = dateFormat.parse(data)
            val calendar = Calendar.getInstance()
            if (newDate != null) {
                calendar.time = newDate
            }
            val thang = calendar.get(Calendar.MONTH)+count
            val year = calendar.get(Calendar.YEAR)

            val c1 = GregorianCalendar(year, thang, 1)
            val  m=(c1.get(Calendar.MONTH)+1).toString()
            val y=c1.get(Calendar.YEAR).toString()
            val newM= if(m.length==1) "0$m" else m
            hienThiThangCu(binding.root.context,maKhu,"$y-$newM")
            binding.tvThangDSHD.text ="Tháng ${m},${y}"

        }


        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "")!!
        list = HoaDonDao(requireContext()).getAllInHoaDonByMaKhu(maKhu)

        onResume()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hienThiThangCu(binding.root.context,maKhu, "$currentYear-$curentMonth")
    }

    override fun onResume() {
        super.onResume()
        hienThiThangCu(binding.root.context,maKhu, "$currentYear-$curentMonth")

    }
    private fun hienThiThangCu(context: Context, maKhu : String, date:String){
        val hoaDonDao = HoaDonDao(context)
        list = hoaDonDao.getAllInHoaDonByMaKhu(maKhu).filter {date in it.ngay_tao_hoa_don}
        val hoaDonDaThanhToanAdapter = HoaDonDaThanhToanAdapter(list)
        binding.rcyHoaDonDaThanhToan.adapter = hoaDonDaThanhToanAdapter
        hoaDonDaThanhToanAdapter.notifyDataSetChanged()

    }
}