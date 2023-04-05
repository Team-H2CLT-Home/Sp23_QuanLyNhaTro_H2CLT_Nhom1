package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.HoaDonDaThanhToanAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HoaDonDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityDoanhThuBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HoaDon
import java.text.SimpleDateFormat
import java.util.*


class ActivityDoanhThu : AppCompatActivity() {
    private lateinit var binding: ActivityDoanhThuBinding
    var dayStart=0
    var monthStart=0
    var yearStart=0
    var dayEnd=0
    var monthEnd=0
    var yearEnd=0
    var dateStart:Any?=null
    var dateEnd:Any?=null
    var ngayDau=""
    var ngayCuoi=""
    var sum=0
    private var maKhu = ""
    private var list = mutableListOf<HoaDon>()
    private val simpleDateFormatNow = SimpleDateFormat("yyyy-MM-dd")
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoanhThuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbDoanhThu
        setSupportActionBar(binding.tbDoanhThu)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        //====================================================
        binding.edNgayBatDauDoanhThu.isVisible = true
        binding.edNgayKetThucDoanhThu.isVisible = true
        binding.recyclerDoanhThu.isVisible=false
        val srf=binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu= srf.getString(MA_KHU_KEY, "")!!

        binding.imgNgayBatDauDoanhThu.setOnClickListener {
            val c = Calendar.getInstance() as GregorianCalendar?
            yearStart = (c as Calendar).get(Calendar.YEAR)
            monthStart = c!!.get(Calendar.MONTH)
            dayStart = c!!.get(Calendar.DAY_OF_MONTH)
            val d = DatePickerDialog(
                this,
                0,
                dateStart as DatePickerDialog.OnDateSetListener?,
                yearStart,
                monthStart,
                dayStart
            )
            d.show()
        }

        binding.imgNgayKetThucDoanhThu.setOnClickListener {
            val c = Calendar.getInstance() as GregorianCalendar?
            yearEnd = (c as Calendar).get(Calendar.YEAR)
            monthEnd = c!!.get(Calendar.MONTH)
            dayEnd = c!!.get(Calendar.DAY_OF_MONTH)
            val d = DatePickerDialog(
                this,
                0,
                dateEnd as DatePickerDialog.OnDateSetListener?,
                yearEnd,
                monthEnd,
                dayEnd
            )
            d.show()


        }

        dateStart = DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            yearStart = i
            monthStart = i1
            dayStart = i2
            val c = GregorianCalendar(yearStart, monthStart, dayStart)
            binding.edNgayBatDauDoanhThu.setText(simpleDateFormat.format(c!!.time))
            ngayDau=simpleDateFormat.format(c!!.time)
        }
        dateEnd = DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
            yearEnd = i
            monthEnd = i1
            dayEnd = i2
            val c = GregorianCalendar(yearEnd, monthEnd, dayEnd)
            binding.edNgayKetThucDoanhThu.setText(simpleDateFormat.format(c!!.time))
            ngayCuoi=simpleDateFormat.format(c!!.time)
        }



        //=======nút doanh thu
        Toast.makeText(binding.root.context, ""+sum, Toast.LENGTH_SHORT).show()

        binding.btnTongDoanhThu.setOnClickListener {
            sum=HoaDonDao(binding.root.context).getAllInHoaDonByDate(ngayDau,ngayCuoi,maKhu)
            if(sum==0){
                binding.recyclerDoanhThu.isVisible=false
            }else{
                binding.tvTongDoanhThu.text = "tổng là: $sum đ"
                binding.recyclerDoanhThu.isVisible=true
                reload()
            }
            Toast.makeText(binding.root.context, ""+sum, Toast.LENGTH_SHORT).show()
        }
    }
    fun chuyenActivity(){
        val intent = Intent(this@ActivityDoanhThu, ActivityManHinhChinhChuTro::class.java)
        startActivity(intent)
        finish()
    }
    override fun  onOptionsItemSelected(item : MenuItem): Boolean {
        val id : Int = item.getItemId();
        if (id==android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item);
    }
    override fun onResume() {
        super.onResume()
        reload()
    }
    private fun reload(){
        list = HoaDonDao(binding.root.context).getAllInHoaDonByMaKhu(maKhu) as MutableList<HoaDon>
        val hoaDonDaThanhToanAdapter  = HoaDonDaThanhToanAdapter(list)
        binding.recyclerDoanhThu.adapter = hoaDonDaThanhToanAdapter

        hoaDonDaThanhToanAdapter.notifyDataSetChanged()
    }

}