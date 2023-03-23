package h2clt.fpt.quanlynhatro_h2clt_nhom1.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import h2clt.fpt.quanlynhatro_h2clt_nhom1.R
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.FILE_NAME
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.MA_KHU_KEY
import h2clt.fpt.quanlynhatro_h2clt_nhom1.adapter.NguoiThueSpinnerAdapter
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.HopDongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.NguoiDungDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.database.PhongDao
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.ActivityActivitytaoHopDongMoiBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.databinding.DialogThemKhachThueHopDongBinding
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.HopDong
import h2clt.fpt.quanlynhatro_h2clt_nhom1.model.NguoiDung
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ActivitytaoHopDongMoi : AppCompatActivity() {
    private lateinit var binding: ActivityActivitytaoHopDongMoiBinding
    var thoiHan = 0
    var mYear = 0
    var mMonth = 0
    var mDay = 0
    var mYearNow = 0
    var mMonthNow = 0
    var mDayNow = 0
    var mYear2 = 0
    var mMonth2 = 0
    var mDay2 = 0
    var mDateNgayO:Any?=null
    var dao:Int = 0
    var listND = listOf<NguoiDung>()
    var listMaphong = listOf<String>()
    var listHopDong= listOf<HopDong>()
    private val simpleDateFormatNow = SimpleDateFormat("yyyy-MM-dd")
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var maND = ""
    private var maPhong = ""
    private var tenPhong = ""
    private var maKhu = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitytaoHopDongMoiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tbTaoHopDongMoi
        setSupportActionBar(binding.tbTaoHopDongMoi)
        val ab = getSupportActionBar()
        ab?.setHomeAsUpIndicator(R.drawable.black_left)
        ab?.setDisplayHomeAsUpEnabled(true)
        //=================================================================
        val srf = binding.root.context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        maKhu = srf.getString(MA_KHU_KEY, "")!!
        val i = intent
        val bundle = i.extras
        if (bundle != null) {
//            var maPhongBundle = bundle.getString("maPhong").toString()
//            var phong: Phong? = PhongDao(this@ActivitytaoHopDongMoi).getIDByTenPhong(maPhongBundle)
//            if (phong != null) {
//                maPhong = phong.ma_phong
//            }
//            Toast.makeText(this@ActivitytaoHopDongMoi,""+maPhong,Toast.LENGTH_SHORT).show()
            tenPhong = bundle.getString("tenPhong").toString()
            maPhong = bundle.getString("maPhong").toString()
            listND = NguoiDungDao(this@ActivitytaoHopDongMoi).getNguoiDungByMaPhong(maPhong)
            Toast.makeText(this@ActivitytaoHopDongMoi, "" + listND.size, Toast.LENGTH_SHORT).show()
            binding.edTenPhongTro.setText(tenPhong)
            val spinner = NguoiThueSpinnerAdapter(this, listND)
            binding.spinnerNguoiDung.adapter = spinner
            binding.spinnerNguoiDung.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    maND = listND[position].ma_nguoi_dung
                    Toast.makeText(this@ActivitytaoHopDongMoi, maND, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
        if (listND.size == 0) {
            binding.tvThemNguoiThue.isVisible = true
            binding.tvThemNguoiThue.setOnClickListener {
                val dialog = DialogThemKhachThueHopDongBinding.inflate(LayoutInflater.from(this))
                val build = AlertDialog.Builder(this).create()
                val listPhong = PhongDao(this).getAllInPhongByMaKhu(maKhu)
//                val spinner=MaPhongSpinner(this, listPhong)
//                dialog.spinnerThemNguoiDung.adapter=spinner
//                dialog.spinnerThemNguoiDung.onItemSelectedListener=object :
//                    AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(
//                        parent: AdapterView<*>?,
//                        view: View?,
//                        position: Int,
//                        id: Long
//                    ) {
//                        maPhong=listPhong[position].ma_phong
//
//                    }
//                    override fun onNothingSelected(parent: AdapterView<*>?) {
//
//                    }
//
//                }
//                build.setView(dialog.root)
//                build.show()
//            }
//                Toast.makeText(this@ActivitytaoHopDongMoi, tenPhong, Toast.LENGTH_SHORT).show()
//                var pos = 0
//                for (i in 0 .. listPhong.size) {
//                    if (maPhong == listPhong[i].ma_phong) {
//                        pos = i
//                    }
//                }
//                dialog.spinnerThemNguoiDung.setSelection(pos)
                dialog.edTenPhong.setText(tenPhong)
                dialog.btnLuuThemNguoiDung.setOnClickListener {
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
                        Toast.makeText(this@ActivitytaoHopDongMoi, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show()
                        onResume()
                        onPause()
                        build.dismiss()

                    } else {
                        Toast.makeText(this@ActivitytaoHopDongMoi, "Thêm người dùng không thành công", Toast.LENGTH_SHORT).show()
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
            }


        }
        binding.edNgayBatDauO.setOnClickListener {
            val c = Calendar.getInstance() as GregorianCalendar?
            mYear = (c as Calendar).get(Calendar.YEAR)
            mMonth = c!!.get(Calendar.MONTH)
            mDay = c!!.get(Calendar.DAY_OF_MONTH)
            val d = DatePickerDialog(
                this,
                0,
                mDateNgayO as DatePickerDialog.OnDateSetListener?,
                mYear,
                mMonth,
                mDay
            )
            d.show()
        }
        binding.edThoiHan.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank() && binding.edNgayBatDauO.text.toString().isNotBlank()) {
                val stringOldDate = binding.edNgayBatDauO.text.toString()
                //val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val newDate = dateFormat.parse(stringOldDate)
                val calendar = Calendar.getInstance()
                if (newDate != null) {
                    calendar.time = newDate
                }
                val month = calendar.get(Calendar.MONTH) + text.toString().toInt()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val year = calendar.get(Calendar.YEAR)
                val c1 = GregorianCalendar(year, month, day)
                binding.edNgayHetHan.setText(simpleDateFormat.format(c1!!.time))
            }
        }
        mDateNgayO = OnDateSetListener { datePicker, i, i1, i2 ->
            mYear = i
            mMonth = i1
            mDay = i2
            val c = GregorianCalendar(mYear, mMonth, mDay)
            binding.edNgayBatDauO.setText(simpleDateFormat.format(c!!.time))
            if (binding.edThoiHan.text?.isNotBlank() == true)
                thoiHan = binding.edThoiHan.text.toString().toInt()
            mYear2 = i
            mMonth2 = i1 + thoiHan
            mDay2 = i2
            val c1 = GregorianCalendar(mYear2, mMonth2, mDay2)
            binding.edNgayHetHan.setText(simpleDateFormat.format(c1!!.time))
        }

        binding.btnLuuHopDong.setOnClickListener {
            val c = Calendar.getInstance() as GregorianCalendar?
            mYearNow = (c as Calendar).get(Calendar.YEAR)
            mMonthNow = c!!.get(Calendar.MONTH)
            mDayNow = c!!.get(Calendar.DAY_OF_MONTH)
            val cNow = GregorianCalendar(mYearNow, mMonthNow, mDayNow)
            val maHopDong = UUID.randomUUID().toString()

            // Validate Ngay bat dau o
            try {
                // Kiểm tra dữ liệu nhập vào
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val dateFormat = android.text.format.DateFormat()
                val objDate = sdf.parse(binding.edNgayBatDauO.getText().toString().trim { it <= ' ' })
                val ngayBatDauO =
                    android.text.format.DateFormat.format("dd/MM/yyyy", objDate) as String
                binding.edNgayBatDauO.setText(ngayBatDauO)
            } catch (ex: Exception) {
                Toast.makeText(
                    this@ActivitytaoHopDongMoi,
                    "Ngày ở không đúng định dạng(dd/MM/yyyy)",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            // Validate Het hop Dong
            try {
                // Kiểm tra dữ liệu nhập vào
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val dateFormat = android.text.format.DateFormat()
                val objDate = sdf.parse(binding.edNgayHetHan.getText().toString().trim { it <= ' ' })
                val ngayBatDauO =
                    android.text.format.DateFormat.format("dd/MM/yyyy", objDate) as String
                binding.edNgayHetHan.setText(ngayBatDauO)
            } catch (ex: Exception) {
                Toast.makeText(
                    this@ActivitytaoHopDongMoi,
                    "Ngày hết hợp đồng không đúng định dạng(dd/MM/yyyy)",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val hopDong = HopDong(
                ma_hop_dong = maHopDong,
                ma_phong = maPhong,
                ma_nguoi_dung = maND,
                thoi_han = binding.edThoiHan.text.toString().toInt(),
                ngay_o = chuyenDinhDangNgay(binding.edNgayBatDauO.text),
                ngay_hop_dong = chuyenDinhDangNgay(binding.edNgayHetHan.text),
                tien_coc = binding.edTienCoc.text.toString().toInt(),
                anh_hop_dong = "aaaa",
                trang_thai_hop_dong = if (binding.chkTrangThai.isChecked) 1 else 0,
                ngay_lap_hop_dong = simpleDateFormatNow.format(cNow!!.time)
            )
            val dao = HopDongDao(this@ActivitytaoHopDongMoi).insertHopDong(hopDong)
            if (dao > 0) {
                Toast.makeText(this@ActivitytaoHopDongMoi, "Thêm hợp đồng thành công", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ActivitytaoHopDongMoi,ActivityTaoHopDong::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@ActivitytaoHopDongMoi, "Thêm hợp đồng không thành công", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnHuyHopDong.setOnClickListener {
            listHopDong = HopDongDao(this@ActivitytaoHopDongMoi).getAllInHopDong()
            Log.d("TAG", "onCreate: " + listHopDong)
        }
    }

    // Chuyen Dinh Dang Ngay
    private fun chuyenDinhDangNgay(text: Editable?):String {
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

    fun chuyenActivity() {
        val intent = Intent(this@ActivitytaoHopDongMoi, ActivityTaoHopDong::class.java)
        startActivity(intent)
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId();
        if (id == android.R.id.home)
            chuyenActivity();
        return super.onOptionsItemSelected(item);
    }
    override fun onResume() {
        super.onResume()
        listND = NguoiDungDao(this@ActivitytaoHopDongMoi).getNguoiDungByMaPhong(maPhong)
        val spinner = NguoiThueSpinnerAdapter(this, listND)
        binding.spinnerNguoiDung.adapter = spinner
        binding.spinnerNguoiDung.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                maND = listND[position].ma_nguoi_dung
                Toast.makeText(this@ActivitytaoHopDongMoi, maND, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: ")
        binding.tvThemNguoiThue.isVisible = false
    }
}

